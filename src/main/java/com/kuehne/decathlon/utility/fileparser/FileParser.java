package com.kuehne.decathlon.utility.fileparser;

import java.io.File;
import java.util.List;

@FunctionalInterface
public interface FileParser<E> {
	
	public void parser(File file,List<E> entity);

}
