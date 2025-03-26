<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Departments</title>
</head>
<body>
    <h2>Department List</h2>
    
    <!-- Add Department Button -->
    <a href="{{ url('/departments/create') }}">Add Department</a>

    <!-- Success Message -->
    @if(session('success'))
        <p style="color: green;">{{ session('success') }}</p>
    @endif

    <table border="1">
        <tr>
            <th>ID</th>
            <th>College ID</th>
            <th>Department Name</th>
            <th>Department Code</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        @foreach($departments as $department)
        <tr>
            <td>{{ $department->id }}</td>
            <td>{{ $department->college_id }}</td>
            <td>{{ $department->department_name }}</td>
            <td>{{ $department->department_code }}</td>
            <td>{{ $department->is_active ? 'Active' : 'Inactive' }}</td>
            <td>
                <a href="{{ url('/departments/'.$department->id.'/edit') }}">Edit</a> |
                <form action="{{ url('/departments/'.$department->id) }}" method="POST" style="display:inline;">
                    @csrf
                    @method('DELETE')
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
        @endforeach
    </table>
</body>
</html>
