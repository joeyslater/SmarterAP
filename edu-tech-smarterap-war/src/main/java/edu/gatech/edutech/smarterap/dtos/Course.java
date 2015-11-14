package edu.gatech.edutech.smarterap.dtos;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

@Entity
@Table(name = "course")
@AutoProperty
public class Course extends BaseDto
{
	@Column
	private String		name;

	@Column
	private String		section;

	@ManyToOne(cascade = CascadeType.ALL)
	private Subject		subject;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "course_owners", joinColumns = @JoinColumn(name = "owned_course_id") , inverseJoinColumns = @JoinColumn(name = "owner_username", referencedColumnName = "username") )
	@Fetch(FetchMode.SELECT)
	private Set<User>	owners		= Sets.newHashSet();

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "course_students", joinColumns = @JoinColumn(name = "student_course_id") , inverseJoinColumns = @JoinColumn(name = "student_username", referencedColumnName = "username") )
	@Fetch(FetchMode.SELECT)
	private Set<User>	students	= Sets.newHashSet();

	//	@JsonIgnore
	//	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	//	@JoinColumn(name = "course_id")
	/*	@Transient
		private Set<Assessment>	assessments;*/

	@Transient
	private Set<String>	ownerNames	= Sets.newHashSet();

	public Course()
	{

	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getSection()
	{
		return section;
	}

	public void setSection(final String section)
	{
		this.section = section;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(final Subject subject)
	{
		this.subject = subject;
	}

	public Set<User> getOwners()
	{
		return owners;
	}

	public void setOwners(final Set<User> owners)
	{
		this.owners = owners;
	}

	public Set<String> getOwnerNames()
	{
		return ownerNames;
	}

	public void setOwnerNames(final Set<String> ownerNames)
	{
		this.ownerNames = ownerNames;
	}

	public Set<User> getStudents()
	{
		return students;
	}

	public void setStudents(final Set<User> students)
	{
		this.students = students;
	}

}
