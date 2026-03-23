package cs520.view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cs520.model.FileExplorerModel;


public class FileExplorerGUI extends JFrame implements PropertyChangeListener
{
	public static final String APP_TITLE = "File Explorer";
	public static final String ABOUT_TITLE = "About";
	public static final String HIDDEN_RESOURCES_MENUITEM_TITLE = "Hidden Resources";
	public static final String SHOW_HIDDEN_RESOURCES_MENUITEM_TITLE = "Show " + HIDDEN_RESOURCES_MENUITEM_TITLE;
	public static final String HIDE_HIDDEN_RESOURCES_MENUITEM_TITLE = "Hide " + HIDDEN_RESOURCES_MENUITEM_TITLE;
	
	private JMenuItem aboutMenuItem;
	private JMenuItem openFolderMenuItem;
	private JMenuItem closeFolderMenuItem;
	private JMenuItem refreshMenuItem;
	private JMenuItem showHiddenResourcesMenuItem;
	private JMenuItem goEnclosingFolderMenuItem;
	private JMenuItem goHomeFolderMenuItem;
	private JMenuItem goDesktopFolderMenuItem;
	private JMenuItem goDocumentsFolderMenuItem;
	private JMenuItem goDownloadsFolderMenuItem;
	private FileExplorerHeaderView topPanel;
	private FileExplorerListView centerPanel;

	private FileExplorerModel model;
	
	public FileExplorerGUI(FileExplorerModel model) {
		this.model = model;

		setTitle(APP_TITLE);
		setSize(800, 600);
		
		JMenuBar topMenuBar = new JMenuBar();
		setJMenuBar(topMenuBar);
		JMenu appMenu = new JMenu(APP_TITLE);
		topMenuBar.add(appMenu);
		this.aboutMenuItem = new JMenuItem(ABOUT_TITLE + " " + APP_TITLE);
		appMenu.add(this.aboutMenuItem);
		JMenu fileMenu = new JMenu("File");
		topMenuBar.add(fileMenu);
		this.openFolderMenuItem = new JMenuItem("Open Folder...");
		fileMenu.add(this.openFolderMenuItem);
		this.refreshMenuItem = new JMenuItem("Refresh");
		fileMenu.add(this.refreshMenuItem);
		fileMenu.addSeparator();
		this.closeFolderMenuItem = new JMenuItem("Close Folder");
		fileMenu.add(this.closeFolderMenuItem);
		JMenu viewMenu = new JMenu("View");
		topMenuBar.add(viewMenu);
		this.showHiddenResourcesMenuItem = new JMenuItem(HIDE_HIDDEN_RESOURCES_MENUITEM_TITLE);
		viewMenu.add(showHiddenResourcesMenuItem);
		JMenu goMenu = new JMenu("Go");
		topMenuBar.add(goMenu);
		this.goEnclosingFolderMenuItem = new JMenuItem("Enclosing Folder");
		goMenu.add(this.goEnclosingFolderMenuItem);
		goMenu.addSeparator();
		this.goHomeFolderMenuItem = new JMenuItem("Home");
		goMenu.add(this.goHomeFolderMenuItem);
		this.goDesktopFolderMenuItem = new JMenuItem("Desktop");
		goMenu.add(this.goDesktopFolderMenuItem);
		this.goDocumentsFolderMenuItem = new JMenuItem("Documents");
		goMenu.add(this.goDocumentsFolderMenuItem);
		this.goDownloadsFolderMenuItem = new JMenuItem("Downloads");
		goMenu.add(this.goDownloadsFolderMenuItem);
		
		BorderLayout jFrameLayoutManager = new BorderLayout();
		setLayout(jFrameLayoutManager);
		// Create the top panel
		this.topPanel = new FileExplorerHeaderView(null);
		add(this.topPanel.getFilePanel(), BorderLayout.NORTH);
		// Create the center panel
		this.centerPanel = new FileExplorerListView(this);
		add(this.centerPanel.getFileListingPanel());
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}
	
	public JMenuItem getAboutMenuItem() {
		return this.aboutMenuItem;
	}
	
	public void showAboutDialog(FileExplorerModel fileManagerModel) {
		JOptionPane.showMessageDialog(this, APP_TITLE + System.getProperty("line.separator") + "OS: " + fileManagerModel.getOSName() + " " + fileManagerModel.getOSVersion(), ABOUT_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public JMenuItem getOpenFolderMenuItem() {
		return this.openFolderMenuItem;
	}
	
	public String showOpenFolderChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		  String openFileName = fileChooser.getSelectedFile().getAbsolutePath();
		  return openFileName;
		}
		return null;
	}
	
	public JMenuItem getRefreshMenuItem() {
		return this.refreshMenuItem;
	}
	
	public JMenuItem getCloseFolderMenuItem() {
		return this.closeFolderMenuItem;
	}
	
	public JMenuItem getShowHiddenResourcesMenuItem() {
		return this.showHiddenResourcesMenuItem;
	}
	
	public void toggleShowHiddenResources() {
		if (this.showHiddenResourcesMenuItem.getText().equals(HIDE_HIDDEN_RESOURCES_MENUITEM_TITLE)) {
			this.showHiddenResourcesMenuItem.setText(SHOW_HIDDEN_RESOURCES_MENUITEM_TITLE);
		}
		else {
			this.showHiddenResourcesMenuItem.setText(HIDE_HIDDEN_RESOURCES_MENUITEM_TITLE);
		}
	}
	
	public JMenuItem getGoEnclosingFolderMenuItem() {
		return this.goEnclosingFolderMenuItem;
	}
	
	public JMenuItem getGoHomeFolderMenuItem() {
		return this.goHomeFolderMenuItem;
	}
	
	public JMenuItem getGoDesktopFolderMenuItem() {
		return this.goDesktopFolderMenuItem;
	}
	
	public JMenuItem getGoDocumentsFolderMenuItem() {
		return this.goDocumentsFolderMenuItem;
	}
	
	public JMenuItem getGoDownloadsFolderMenuItem() {
		return this.goDownloadsFolderMenuItem;
	}
	
	public FileExplorerListView getFileExplorerFileListing() {
		return this.centerPanel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		this.update();
		event.getNewValue();
	}
	
	public void update() {
		// Update the "Go Enclosing Folder" menu item
		if ((this.model != null) && (this.goEnclosingFolderMenuItem != null)) {
			boolean enabled;
			if ((this.model.getCurrentOpenFolder() == null) || (this.model.getCurrentOpenFolder().getParent() == null)) {
				enabled = false;
			}
			else {
				enabled = true;
			}
			this.goEnclosingFolderMenuItem.setEnabled(enabled);
		}
		// Update the top panel
		this.topPanel.update(this.model);
		// Update the center panel
		this.centerPanel.update(this.model);
	}
}
