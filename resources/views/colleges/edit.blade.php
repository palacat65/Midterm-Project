@extends('layouts.app')

@section('content')
<h2>Edit College</h2>
<form action="{{ route('colleges.update', $college->id) }}" method="POST">
    @csrf
    @method('PUT')
    <label>Names:</label>
    <input type="text" name="college_name" value="{{ $college->college_name }}" required>
    <label>Code:</label>
    <input type="text" name="college_code" value="{{ $college->college_code }}" required>
    <input type="checkbox" name="is_active" {{ $college->is_active ? 'checked' : '' }}> Active
    <button type="submit" class="btn btn-success">Update</button>
</form>
@endsection
