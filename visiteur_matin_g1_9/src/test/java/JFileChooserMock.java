import java.io.File;

import javax.swing.JFileChooser;

public class JFileChooserMock extends JFileChooser {

    private int showDialogReturnValue;
    private File selectedFile;
    private File writeOutput;

    @Override
    public int showSaveDialog(java.awt.Component parent) {
        return showDialogReturnValue;
    }

    @Override
    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFileForMocking(File selectedFile) {
        this.selectedFile = selectedFile;
    }
    public void setShowDialogReturnValue(int showDialogReturnValue) {
        this.showDialogReturnValue = showDialogReturnValue;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public void setWriteOutput(File writeOutput) {
        this.writeOutput = writeOutput;
    }

    public File getWriteOutput() {
        return writeOutput;
    }
}

