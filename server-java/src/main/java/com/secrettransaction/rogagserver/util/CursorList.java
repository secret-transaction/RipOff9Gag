package com.secrettransaction.rogagserver.util;

import java.util.List;

import com.google.appengine.api.datastore.Cursor;

public interface CursorList<T> {

	List<T> getResults();
	Cursor getCursor();
	
}
