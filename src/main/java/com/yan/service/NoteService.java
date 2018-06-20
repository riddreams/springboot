package com.yan.service;

import com.yan.model.Note;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author lwyan on 2018-06-06 11:49
 */
public interface NoteService {

	List<Note> listNote(int begin, int page);

	void insertNote(String userName, String says, Timestamp timestamp);

	int countNote();
}