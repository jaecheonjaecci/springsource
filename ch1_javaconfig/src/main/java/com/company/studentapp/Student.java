package com.company.studentapp;

public class Student {
	private String name;
	private String age;
	private String GradeName;
	private String classNum;
	
	public Student(String name, String age, String GradeName, String classNum) {
		super();
		this.name = name;
		this.age = age;
		this.GradeName = GradeName;
		this.classNum = classNum;
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public String getGradeName() {
		return GradeName;
	}

	public String getClassNum() {
		return classNum;
	}

}
