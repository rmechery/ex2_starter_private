package cs520.view;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cs520.model.FileExplorerModel;
import cs520.model.FileModel;


/**
 * Provides support for performing black box testing of the 
 * view part of the MVC architecture pattern.
 */
public class FileExplorerViewTests {
	private FileExplorerModel model;
	private FileExplorerGUI gui;
	
	@BeforeEach
	public void setUp() {
		this.model = new FileExplorerModel();
		this.gui = new FileExplorerGUI(model);
	}
	
	protected void testFileExplorerGUIHelper() {
		assertNotNull(this.gui);
		assertEquals(0, this.gui.getFileExplorerFileListing().getFileListingModel().getSize());
	}
	
	@Test
	public void testFileExplorerGUI() {
		this.testFileExplorerGUIHelper();
	}
	
	protected void testUpdateNotNullHelper() {
		this.testFileExplorerGUIHelper();
		FileModel testFilesFolderModel = new FileModel("test_files");
		this.model.go(testFilesFolderModel);
		this.gui.update();
		assertEquals(2, this.gui.getFileExplorerFileListing().getFileListingModel().getSize());
	}
	
	@Test
	public void testUpdateNotNull() {
		this.testUpdateNotNullHelper();
		this.model.go(null);
		this.gui.update();
		this.testFileExplorerGUIHelper();
	}
	
	@Test
	public void testUpdateNotNullThenNull() {
		this.testUpdateNotNullHelper();
		model.go(null);
		
	}
	
	@AfterEach
	public void tearDown() {
		this.gui = null;
	}
}
