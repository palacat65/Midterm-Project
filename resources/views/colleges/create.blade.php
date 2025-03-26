@extends('layouts.app')

@section('content')
<div class="container mt-4">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Add New Colleges</h4>
        </div>
        <div class="card-body">
            <form action="{{ route('colleges.store') }}" method="POST">
                @csrf
                <div class="mb-3">
                    <label for="college_code" class="form-label fw-bold">College Codes</label>
                    <input type="text" class="form-control @error('college_code') is-invalid @enderror" 
                           id="college_code" name="college_code" required>
                    @error('college_code')
                        <div class="invalid-feedback">{{ $message }}</div>
                    @enderror
                </div>

                <div class="mb-3">
                    <label for="college_name" class="form-label fw-bold">College Names</label>
                    <input type="text" class="form-control @error('college_name') is-invalid @enderror" 
                           id="college_name" name="college_name" required>
                    @error('college_name')
                        <div class="invalid-feedback">{{ $message }}</div>
                    @enderror
                </div>

                <button type="submit" class="btn btn-success w-100">Save College</button>
            </form>
        </div>
    </div>
</div>
@endsection
