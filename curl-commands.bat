@echo off
echo ========================================
echo cURL Tests
echo ========================================
echo.

echo [1] Create Task
curl -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d "{\"title\":\"Test Task\",\"description\":\"Test description\",\"status\":\"PENDING\",\"dueDate\":\"2024-09-15\"}"
echo.
echo.

echo [2] Get Task
curl -X GET http://localhost:8080/tasks/1
echo.
echo.

echo [3] Create User
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d "{\"username\":\"alice\",\"password\":\"password123\"}"
echo.
echo.

echo [4] Get All Users
curl -X GET http://localhost:8080/users
echo.
echo.

echo [5] Get User
curl -X GET http://localhost:8080/users/1
echo.
echo.

echo [6] Update User
curl -X PUT http://localhost:8080/users/1 -H "Content-Type: application/json" -d "{\"username\":\"alice_updated\",\"password\":\"newpass456\"}"
echo.
echo.

echo [7] Get All Priorities
curl -X GET http://localhost:8080/priorities
echo.
echo.

echo [8] Get Priority 1 (LOW)
curl -X GET http://localhost:8080/priorities/1
echo.
echo.

echo [9] Get Priority 2 (MEDIUM)
curl -X GET http://localhost:8080/priorities/2
echo.
echo.

echo [10] Get Priority 3 (HIGH)
curl -X GET http://localhost:8080/priorities/3
echo.
echo.

echo [11] Create Task with HIGH Priority
curl -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d "{\"title\":\"Finish project\",\"description\":\"Complete milestone 1\",\"status\":\"PENDING\",\"dueDate\":\"2024-09-30\",\"priority\":{\"id\":3}}"
echo.
echo.

echo [12] Get Task 2
curl -X GET http://localhost:8080/tasks/2
echo.
echo.

echo [13] Create Subtask
curl -X POST http://localhost:8080/subtasks -H "Content-Type: application/json" -d "{\"title\":\"Write unit tests\",\"status\":\"PENDING\",\"task\":{\"id\":1}}"
echo.
echo.

echo [14] Get All Subtasks
curl -X GET http://localhost:8080/subtasks
echo.
echo.

echo [15] Get Subtask
curl -X GET http://localhost:8080/subtasks/1
echo.
echo.

echo [16] Update Subtask
curl -X PUT http://localhost:8080/subtasks/1 -H "Content-Type: application/json" -d "{\"title\":\"Write unit tests\",\"status\":\"COMPLETED\",\"task\":{\"id\":1}}"
echo.
echo.

echo [17] Delete Subtask
curl -X DELETE http://localhost:8080/subtasks/1
echo.
echo.

echo [18] Verify Subtask Was Deleted
curl -X GET http://localhost:8080/subtasks/1
echo.
echo.

echo ========================================
echo Tests complete.
echo ========================================
pause
