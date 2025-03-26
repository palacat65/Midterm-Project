<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CollegeController;
use App\Http\Controllers\DepartmentController;

Route::get('/', function () {
    return view('welcome');
});

Route::resource('colleges', CollegeController::class);
Route::resource('departments', DepartmentController::class);
Route::get('/departments', [DepartmentController::class, 'index'])->name('departments.index');
Route::get('/departments', [DepartmentController::class, 'index']);
Route::get('/departments/create', [DepartmentController::class, 'create']);
Route::post('/departments', [DepartmentController::class, 'store']);
Route::get('/departments/{id}/edit', [DepartmentController::class, 'edit']);
Route::put('/departments/{id}', [DepartmentController::class, 'update']);
Route::delete('/departments/{id}', [DepartmentController::class, 'destroy']);
