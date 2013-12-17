import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SaveLevelDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaveLevel;
	private CreateNewLevel createLevel;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// SaveLevelDialog dialog = new SaveLevelDialog();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog.
	 */
	public SaveLevelDialog(CreateNewLevel level) {
		createLevel = level;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblGiveAName = new JLabel("Give a name to new level");
		lblGiveAName.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSaveLevel = new JTextField();
		txtSaveLevel.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addGap(82)
						.addGroup(
								gl_contentPanel
										.createParallelGroup(
												Alignment.TRAILING, false)
										.addComponent(txtSaveLevel,
												Alignment.LEADING)
										.addComponent(lblGiveAName,
												Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addContainerGap(115, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addGap(23)
						.addComponent(lblGiveAName)
						.addGap(44)
						.addComponent(txtSaveLevel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(107, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String fileName = txtSaveLevel.getText();
						JButton[][] bricks = createLevel.getBricks();
						try {
							DocumentBuilderFactory docFactory = DocumentBuilderFactory
									.newInstance();
							DocumentBuilder docBuilder = docFactory
									.newDocumentBuilder();
							Document doc = docBuilder.newDocument();
							Element rootElement = doc.createElement("level");
							rootElement.setAttribute("name", fileName);
							doc.appendChild(rootElement);
							for (int i = 0; i < 10; i++) {
								for (int j = 0; j < 10; j++) {
									String[] brickFeatures = bricks[i][j].getToolTipText().split("-");
									if (brickFeatures.length > 2) {
										Element brick = doc.createElement("Brick");
										rootElement.appendChild(brick);
										brick.setAttribute("x", brickFeatures[1]);
										brick.setAttribute("y", brickFeatures[0]);
										brick.setAttribute("Type", brickFeatures[3]);

										Element bonus = doc.createElement("Bonus");
										brick.appendChild(bonus);
										bonus.setAttribute("Type", brickFeatures[2]);
									}
								}
							}
							TransformerFactory transformerFactory = TransformerFactory.newInstance();
							Transformer transformer = transformerFactory.newTransformer();
							DOMSource source = new DOMSource(doc);
							StreamResult result = new StreamResult(new File("bin/maps/custom/" + fileName + ".xml"));
					 
					 
							transformer.transform(source, result);
						} catch (ParserConfigurationException pce) {
							pce.printStackTrace();
						} catch (TransformerConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TransformerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
