package edu.gatech.edutech.smarterap.dtos;

import org.springframework.web.multipart.MultipartFile;

public class BlackboardFile
{
	private MultipartFile file;

	public MultipartFile getFile()
	{
		return file;
	}

	public void setFile(final MultipartFile file)
	{
		this.file = file;
	}

}
