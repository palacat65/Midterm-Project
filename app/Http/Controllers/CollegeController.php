<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\College;

class CollegeController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $colleges = College::where('is_active', 1)->get();
        return view('colleges.index', compact('colleges'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('colleges.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $request->validate([
            'college_code' => 'required|string|unique:colleges',
            'college_name' => 'required|string',
        ]);

        College::create([
            'college_name' => $request->college_name,
            'college_code' => $request->college_code,
            'is_active' => $request->has('is_active'),
        ]);

        return redirect()->route('colleges.index')->with('success', 'College added successfully!');
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit($id)
    {
        $college = College::find($id); // ✅ Fetch the college from the database

        if (!$college) { // ✅ If not found, return an error
            return redirect()->route('colleges.index')->with('error', 'College not found.');
        }
        
        return view('colleges.edit', compact('college'));
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, College $college)
    {
        $request->validate([
            'college_name' => 'required',
            'college_code' => 'required|unique:colleges,college_code,' . $college->id,
        ]);

        $college->update([
            'college_name' => $request->college_name,
            'college_code' => $request->college_code,
            'is_active' => $request->has('is_active'),
        ]);

        return redirect()->route('colleges.index')->with('success', 'College updated!');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
{
    $college = College::find($id); // ✅ Find the college

    if (!$college) { // ✅ If not found, return an error
        return redirect()->route('colleges.index')->with('error', 'College not found.');
    }

    try {
        $college->delete(); // ✅ Attempt to delete
        return redirect()->route('colleges.index')->with('success', 'College deleted successfully.');
    } catch (\Exception $e) {
        return redirect()->route('colleges.index')->with('error', 'Error deleting college: ' . $e->getMessage());
    }
}
}
