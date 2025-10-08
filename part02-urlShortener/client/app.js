"use strict";

const API_BASE_URL = "http://localhost:8082/api/v1/urls";

const shortenForm = document.getElementById("shorten-form");
const originalUrlInput = document.getElementById("original-url-input");
const shortenResultDiv = document.getElementById("shorten-result");

const analyticsForm = document.getElementById("analytics-form");
const shortKeyInput = document.getElementById("short-key-input");
const analyticsResultDiv = document.getElementById("analytics-result");

/**
 * Handles the form submission for shortening a URL.
 */
const shortenUrl = async (event) => {
  event.preventDefault();
  const originalUrl = originalUrlInput.value;

  try {
    const response = await fetch(API_BASE_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ originalUrl: originalUrl }),
    });
    const result = await response.json();

    // highlight-start
    // Check the response code and access the data from result.data
    if (result.code === 200) {
      const shortUrl = result.data.shortUrl;
      shortenResultDiv.innerHTML = `
                <strong>Short URL created:</strong> 
                <a href="${shortUrl}" target="_blank">${shortUrl}</a>
            `;
    } else {
      shortenResultDiv.textContent = `Error: ${result.message}`;
    }
    // highlight-end
  } catch (error) {
    console.error("Error shortening URL:", error);
    shortenResultDiv.textContent = "Error: Could not shorten URL.";
  }
};

/**
 * Handles the form submission for checking URL analytics.
 */
const getAnalytics = async (event) => {
  event.preventDefault();
  const shortKey = shortKeyInput.value.trim();

  if (!shortKey) {
    analyticsResultDiv.textContent = "Please enter a short key.";
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/${shortKey}`);
    const result = await response.json();

    // highlight-start
    // Check the response code and access the data from result.data
    if (result.code === 200) {
      const analyticsData = result.data;
      analyticsResultDiv.innerHTML = `
                <p><strong>Original URL:</strong> ${analyticsData.originalUrl}</p>
                <p><strong>Short URL:</strong> ${analyticsData.shortUrl}</p>
                <p><strong>Click Count:</strong> ${analyticsData.clickCount}</p>
            `;
    } else {
      analyticsResultDiv.textContent = `Error: ${result.message}`;
    }
    // highlight-end
  } catch (error) {
    console.error("Error fetching analytics:", error);
    analyticsResultDiv.textContent = "Error: Could not fetch analytics. Invalid key?";
  }
};

// Add event listeners to the forms.
shortenForm.addEventListener("submit", shortenUrl);
analyticsForm.addEventListener("submit", getAnalytics);
