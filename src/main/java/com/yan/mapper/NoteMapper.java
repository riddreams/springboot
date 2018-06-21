package com.yan.mapper;

import com.yan.model.Note;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author lwyan on 2018-06-06 11:50
 */
@Repository
public interface NoteMapper {

	List<Note> listNote(RowBounds rowBounds);

	List<Note> listNoteByName(@Param("name")String name);

	void insertNote(@Param("userName") String userName, @Param("says") String says, @Param("timing") Timestamp timestamp);

	int countNote();
}