package a03;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class ExplorerIO {

	public File loadDir() throws IOException {

		JFileChooser fc = new JFileChooser();

		fc.setDialogTitle("Verzeichnis wählen...");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

		}
		return fc.getSelectedFile();

	}

}

//Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
//at a03.ExplorerTree$1.valueChanged(ExplorerTree.java:84)
//at javax.swing.JTree.fireValueChanged(Unknown Source)
//at javax.swing.JTree$TreeSelectionRedirector.valueChanged(Unknown Source)
//at javax.swing.tree.DefaultTreeSelectionModel.fireValueChanged(Unknown Source)
//at javax.swing.tree.DefaultTreeSelectionModel.notifyPathChange(Unknown Source)
//at javax.swing.tree.DefaultTreeSelectionModel.removeSelectionPaths(Unknown Source)
//at javax.swing.JTree.removeDescendantSelectedPaths(Unknown Source)
//at javax.swing.JTree.setExpandedState(Unknown Source)
//at javax.swing.JTree.collapsePath(Unknown Source)
//at javax.swing.plaf.basic.BasicTreeUI.toggleExpandState(Unknown Source)
//at javax.swing.plaf.basic.BasicTreeUI.handleExpandControlClick(Unknown Source)
//at javax.swing.plaf.basic.BasicTreeUI.checkForClickInExpandControl(Unknown Source)
//at javax.swing.plaf.basic.BasicTreeUI$Handler.handleSelection(Unknown Source)
//at javax.swing.plaf.basic.BasicTreeUI$Handler.mousePressed(Unknown Source)
//at java.awt.Component.processMouseEvent(Unknown Source)
//at javax.swing.JComponent.processMouseEvent(Unknown Source)
//at java.awt.Component.processEvent(Unknown Source)
//at java.awt.Container.processEvent(Unknown Source)
//at java.awt.Component.dispatchEventImpl(Unknown Source)
//at java.awt.Container.dispatchEventImpl(Unknown Source)
//at java.awt.Component.dispatchEvent(Unknown Source)
//at java.awt.LightweightDispatcher.retargetMouseEvent(Unknown Source)
//at java.awt.LightweightDispatcher.processMouseEvent(Unknown Source)
//at java.awt.LightweightDispatcher.dispatchEvent(Unknown Source)
//at java.awt.Container.dispatchEventImpl(Unknown Source)
//at java.awt.Window.dispatchEventImpl(Unknown Source)
//at java.awt.Component.dispatchEvent(Unknown Source)
//at java.awt.EventQueue.dispatchEvent(Unknown Source)
//at java.awt.EventDispatchThread.pumpOneEventForFilters(Unknown Source)
//at java.awt.EventDispatchThread.pumpEventsForFilter(Unknown Source)
//at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
//at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
//at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
//at java.awt.EventDispatchThread.run(Unknown Source)

