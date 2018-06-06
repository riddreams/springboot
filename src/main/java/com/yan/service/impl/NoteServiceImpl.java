package com.yan.service.impl;

import com.yan.mapper.NoteMapper;
import com.yan.model.Note;
import com.yan.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author lwyan on 2018-06-06 11:50
 */
@Service
public class NoteServiceImpl implements NoteService{
	private NoteMapper noteMapper;
	@Autowired
	public void setNoteMapper(NoteMapper noteMapper) {
		this.noteMapper = noteMapper;
	}

	@Override
	public List<Note> listNote() {
		return noteMapper.listNote();
	}

	@Override
	public void insertNote(String userName, String says, Timestamp timestamp) {
		noteMapper.insertNote(userName,says,timestamp);
	}
}