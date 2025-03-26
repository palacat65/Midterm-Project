<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Department</title>
</head>
<body>
    <h2>Edit Department</h2>

    <form action="{{ url('/departments/'.$department->id) }}" method="POST">
        @csrf
        @method('PUT')

        <label>College ID:</label>
        <input type="number" name="college_id" value="{{ $department->college_id }}" required><br>

        <label>Department Name:</label>
        <input type="text" name="department_name" value="{{ $department->department_name }}" required><br>

        <label>Department Code:</label>
        <input type="text" name="department_code" value="{{ $department->department_code }}" required><br>

        <button type="submit">Update</button>
    </form>
</body>
</html>
