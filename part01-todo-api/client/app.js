"use strict";

// The base URL of the Spring Boot API server.
// Make sure the port number matches your `application-dev.properties`.
const API_BASE_URL = "http://localhost:8081/api/todos";

// Get references to the DOM elements.
const todoList = document.getElementById("todo-list");
const todoForm = document.getElementById("add-todo-form");
const titleInput = document.getElementById("todo-title");
const descInput = document.getElementById("todo-desc");

/**
 * Fetches all todos from the server and renders them.
 */
const fetchTodos = async () => {
  console.log("[fetchTodos]start");
  try {
    const response = await fetch(API_BASE_URL);
    const result = await response.json();
    console.log("[fetchTodos]result : ", result);
    // Check if the request was successful
    if (result.code === 200) {
      renderTodos(result.data);
    } else {
      console.error("Failed to fetch todos :", result.message);
    }
  } catch (error) {
    console.error("Error fetching todos :", error);
  }
};

/**
 * Renders the list of todos on the page.
 * @param {Array} todos - An array of todo objects.
 */
const renderTodos = (todos) => {
  console.log("[renderTodos]start");
  console.log("[renderTodos]todos : ", todos);
  // Clear the existing list
  todoList.innerHTML = "";

  todos.forEach((todo) => {
    // Create the main list item element
    const li = document.createElement("li");
    if (todo.completed) {
      li.classList.add("completed");
    }

    // Create a container for the title and description
    const contentDiv = document.createElement("div");
    contentDiv.className = "content";
    contentDiv.innerHTML = `
            <h3>${todo.title}</h3>
            <p>${todo.description || ""}</p>
        `;

    // Create a container for the action buttons
    const actionsDiv = document.createElement("div");
    actionsDiv.className = "actions";

    // Create Toggle Button
    const toggleBtn = document.createElement("button");
    toggleBtn.textContent = todo.completed ? "Undo" : "Complete";
    toggleBtn.className = "toggle-btn";
    toggleBtn.addEventListener("click", () => toggleTodo(todo.id));

    // Create Edit Button
    const editBtn = document.createElement("button");
    editBtn.textContent = "Edit";
    editBtn.className = "edit-btn";
    editBtn.addEventListener("click", () => editTodoHandler(todo.id, todo.title, todo.description));

    // Create Delete Button
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.className = "delete-btn";
    deleteBtn.addEventListener("click", () => deleteTodo(todo.id));

    // Append buttons to the actions container
    actionsDiv.appendChild(toggleBtn);
    actionsDiv.appendChild(editBtn);
    actionsDiv.appendChild(deleteBtn);

    // Append content and actions to the list item
    li.appendChild(contentDiv);
    li.appendChild(actionsDiv);

    // Append the list item to the main list
    todoList.appendChild(li);
  });
};

/**
 * Handles adding a new todo.
 * @param {Event} event - The form submission event.
 */
const addTodo = async (event) => {
  event.preventDefault(); // Prevent the default form submission behavior.

  const newTodo = {
    title: titleInput.value,
    description: descInput.value,
  };

  try {
    await fetch(API_BASE_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newTodo),
    });
    // Clear the input fields and refresh the list
    titleInput.value = "";
    descInput.value = "";
    fetchTodos();
  } catch (error) {
    console.error("Error adding todo:", error);
  }
};

/**
 * Handles deleting a todo.
 * @param {number} id - The ID of the todo to delete.
 */
const deleteTodo = async (id) => {
  console.log("[deleteTodo]start");
  if (!confirm("Are you sure you want to delete this todo?")) return;

  try {
    await fetch(`${API_BASE_URL}/${id}/delete`, { method: "DELETE" });
    fetchTodos(); // Refresh the list
  } catch (error) {
    console.error("Error deleting todo:", error);
  }
};

/**
 * Handles toggling the completion status of a todo.
 * @param {number} id - The ID of the todo to toggle.
 */
const toggleTodo = async (id) => {
  console.log("[toggleTodo]start");
  try {
    await fetch(`${API_BASE_URL}/${id}/toggle`, { method: "PATCH" });
    fetchTodos(); // Refresh the list
  } catch (error) {
    console.error("Error toggling todo:", error);
  }
};

/**
 * Handles the user interaction for editing a todo.
 * @param {number} id - The ID of the todo to edit.
 * @param {string} currentTitle - The current title of the todo.
 * @param {string} currentDesc - The current description of the todo.
 */
const editTodoHandler = (id, currentTitle, currentDesc) => {
  const newTitle = prompt("Enter the new title:", currentTitle);
  // If the user cancels the prompt, newTitle will be null.
  if (newTitle === null) return;

  const newDesc = prompt("Enter the new description:", currentDesc);
  if (newDesc === null) return;

  editTodo(id, { title: newTitle, description: newDesc });
};

/**
 * Sends the update request to the server.
 * @param {number} id - The ID of the todo to edit.
 * @param {object} updatedTodo - An object with the new title and description.
 */
const editTodo = async (id, updatedTodo) => {
  console.log("[editTodo]start");
  console.log("[editTodo]id : ", id);
  console.log("[editTodo]updatedTodo : ", updatedTodo);
  try {
    await fetch(`${API_BASE_URL}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedTodo),
    });
    fetchTodos(); // Refresh the list
  } catch (error) {
    console.error("Error editing todo:", error);
  }
};

// Add event listener for the form submission.
todoForm.addEventListener("submit", addTodo);

// Initial fetch of todos when the page loads.
fetchTodos();
