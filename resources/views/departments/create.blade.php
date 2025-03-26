@extends('layouts.app')

@section('content')
<div class="container">
    <h2>Add Department</h2>

    <!-- Display Validation Errors -->
    @if ($errors->any())
        <div class="alert alert-danger">
            <ul>
                @foreach ($errors->all() as $error)
                    <li>{{ $error }}</li>
                @endforeach
            </ul>
        </div>
    @endif

    <!-- Display Success Message -->
    @if(session('success'))
        <div class="alert alert-success">{{ session('success') }}</div>
    @endif

    <form action="{{ route('departments.store') }}" method="POST">
        @csrf  <!-- CSRF Protection -->
        <div class="form-group">
            <label for="college_id">Colleges:</label>
            <select name="college_id" class="form-control" required>
                @foreach($colleges as $college)
                    <option value="{{ $college->id }}">{{ $college->college_name }}</option>
                @endforeach
            </select>
        </div>

        <div class="form-group">
            <label for="department_name">Departments Names:</label>
            <input type="text" name="department_name" class="form-control" required>
        </div>


        <div class="form-group">
            <label for="department_code">Department Codes:</label>
            <input type="text" name="department_code" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Departments</button>
    </form>
</div>
@endsection
