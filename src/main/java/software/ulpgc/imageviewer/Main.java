package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.control.NextImageCommand;
import software.ulpgc.imageviewer.control.PreviousImageCommand;
import software.ulpgc.imageviewer.swing.MainFrame;

import javax.swing.*;
import java.io.File;

public class Main {
    public static String root;
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            root = selectedDirectory.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(frame, "Escoja un directorio válido", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
        Image image = new FileImageLoader(new File(root)).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }}
