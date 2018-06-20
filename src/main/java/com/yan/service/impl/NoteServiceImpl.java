package com.yan.service.impl;

import com.yan.mapper.NoteMapper;
import com.yan.model.Note;
import com.yan.service.NoteService;
import org.apache.ibatis.session.RowBounds;
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
	public List<Note> listNote(int begin, int page) {
		RowBounds rowBounds = new RowBounds(begin,page);
		return noteMapper.listNote(rowBounds);
	}

	@Override
	public void insertNote(String userName, String says, Timestamp timestamp) {
		noteMapper.insertNote(userName,says,timestamp);
	}

	@Override
	public int countNote() {
		return noteMapper.countNote();
	}
}