@extends('layouts.app')

@section('content')
<div class="container">
    <h2>Colleges List</h2>
    <a href="{{ route('colleges.create') }}" class="btn btn-success mb-3">Add New College</a>
    
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>College Codes</th>
                <th>College Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($colleges as $college)
                <tr>
                    <td>{{ $college->id }}</td>
                    <td>{{ $college->college_code }}</td>
                    <td>{{ $college->college_name }}</td>
                    <td>
                        <a href="{{ route('colleges.edit', $college->id) }}" class="btn btn-warning btn-sm">Edit</a>
                        <form action="{{ route('colleges.destroy', $college->id) }}" method="POST" onsubmit="return confirm('Are you sure?');">
                        @csrf
                        @method('DELETE')
                        <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            @endforeach
        </tbody>
    </table>
</div>
@endsection
