package cs520.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs520.model.FileExplorerModel;
import cs520.model.FileModel;
import cs520.view.FileExplorerGUI;


public class FileExplorerController 
{
	private FileExplorerModel model;
	private FileExplorerGUI view;
	
	public FileExplorerController() {
		this.model = new FileExplorerModel();
		this.view = new FileExplorerGUI(this.model);
		this.model.addPropertyChangeListener(this.view);
		
		view.getAboutMenuItem().addActionListener(e -> {
			view.showAboutDialog(model);
		});
		
		view.getOpenFolderMenuItem().addActionListener(e -> {
			this.openFolder();
		});
		
		view.getRefreshMenuItem().addActionListener(e -> {
			this.refresh();
		});
		
		view.getCloseFolderMenuItem().addActionListener(e -> {
			this.closeFolder();
		});
		
		view.getShowHiddenResourcesMenuItem().addActionListener(e -> {
			this.toggleShowHiddenResources();
		});
		
		view.getGoEnclosingFolderMenuItem().addActionListener(e -> {
			this.goEnclosingFolder();
		});
		
		view.getGoHomeFolderMenuItem().addActionListener(e -> {
			this.goHomeFolder();
		});
		
		view.getGoDesktopFolderMenuItem().addActionListener(e -> {
			this.goDesktopFolder();
		});
		
		view.getGoDocumentsFolderMenuItem().addActionListener(e -> {
			this.goDocumentsFolder();
		});	
		
		view.getGoDownloadsFolderMenuItem().addActionListener(e -> {
			this.goDownloadsFolder();
		});
		
        // Add a MouseListener for double-click events in the file listing
        view.getFileExplorerFileListing().getFileListingView().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click detected
                    int index = view.getFileExplorerFileListing().getFileListingView().locationToIndex(e.getPoint());
                    if (index >= 0) {
                        FileModel selectedFileModel = view.getFileExplorerFileListing().getFileListingModel().getElementAt(index);
                        if (selectedFileModel.isDirectory()) {
                        	go(selectedFileModel);
                        }
                    }
                }
            }
        });
	}

	public void go(FileModel currentOpenFolder) {
		this.model.go(currentOpenFolder);
		// this.view.update(this.model);
	}
	
	public void openFolder() {
		String openFolderName = view.showOpenFolderChooser();
		if (openFolderName != null) {
			FileModel openFolderModel = new FileModel(openFolderName);
			this.go(openFolderModel);
		}
	}
	
	public void refresh() {
		// The model will load the folder on demand.
		this.view.update();
	}
	
	public void closeFolder() {
		this.model.go(null);
		// this.view.update(this.model);
	}
	
	public void toggleShowHiddenResources() {
		this.view.toggleShowHiddenResources();
		// this.view.update(model);
	}
	
	public void goEnclosingFolder() {
		if (this.model.getCurrentOpenFolder() != null) {
			FileModel enclosingFolder = (FileModel)this.model.getCurrentOpenFolder().getParentFile();
			if (enclosingFolder != null) {
				this.go(enclosingFolder);
			}
		}
	}
	
	public void goDesktopFolder() {
		FileModel desktopFolderModel = new FileModel(model.getDesktopFolderName());
		this.go(desktopFolderModel);		
	}

	public void goDocumentsFolder() {
		FileModel documentsFolderModel = new FileModel(model.getDocumentsFolderName());
		this.go(documentsFolderModel);		
	}
	
	public void goDownloadsFolder() {
		FileModel downloadsFolderModel = new FileModel(model.getDownloadsFolderName());
		this.go(downloadsFolderModel);
	}

	public void goHomeFolder() {
		FileModel homeFolderModel = new FileModel(model.getHomeFolderName());
		this.go(homeFolderModel);
	}
}
