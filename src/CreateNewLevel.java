import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;


public class CreateNewLevel extends JFrame {

	private JPanel contentPane;
	private final String NORMAL_BRICK_PATH = "images/normal_brick.png";
	private final String ROCK_BRICK_PATH = "images/rock_brick.png";
	private final String IRON_BRICK_PATH = "images/iron_brick.png";
	private final String BOMB_BRICK_PATH = "images/bomb_brick.png";
	private String xmlName;
	private JButton[][] bricks;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewLevel frame = new CreateNewLevel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateNewLevel() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblBrickType = new JLabel("Brick Type");
		
		String[] brickTypes = {"Normal", "Rock", "Iron", "Bomb", "None"};
		final JComboBox cbxBrickType = new JComboBox( brickTypes);
		
		String[] bonus = {"None", "Longer Paddle", "Smaller Paddle", "Gun", "Magnet", "Miniball", "Megaball",
				"Slow Ball", "Fast Ball", "Fiery Ball", "Clone Ball", "Reaper", "Extra Soul", "Random", 
				"Roulette", "Crazy Walls", "Score", "Double Score", "Colander"};
		JLabel lblBonusType = new JLabel("Bonus");
		final JComboBox cbxBonus = new JComboBox( bonus);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SaveLevelDialog save = new SaveLevelDialog(CreateNewLevel.this);
				save.setVisible(true);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBrickType)
					.addGap(18)
					.addComponent(cbxBrickType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(87)
					.addComponent(lblBonusType)
					.addGap(18)
					.addComponent(cbxBonus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(btnSave)
					.addContainerGap(191, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBrickType)
						.addComponent(cbxBrickType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBonusType)
						.addComponent(cbxBonus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave))
					.addGap(49))
		);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(10, 10));
		bricks = new JButton[10][10];
		for( int i = 0; i < 10; i++)
		{
			for( int j = 0; j < 10; j++)
			{
				final JButton button = new JButton();
				button.setToolTipText( i + "-" + j);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String bonus = (String) cbxBonus.getSelectedItem();
						button.setToolTipText( button.getToolTipText() + "-" + bonus);
						String brick = (String) cbxBrickType.getSelectedItem();
						switch( brick)
						{
						case "Normal":
							button.setIcon( new ImageIcon( getClass().getResource( NORMAL_BRICK_PATH)));
							button.setToolTipText( button.getToolTipText() + "-" + brick);
							break;
						case "Iron":
							button.setIcon( new ImageIcon( getClass().getResource( IRON_BRICK_PATH)));
							button.setToolTipText( button.getToolTipText() + "-" + brick);
							break;
						case "Rock":
							button.setIcon( new ImageIcon( getClass().getResource( ROCK_BRICK_PATH)));
							button.setToolTipText( button.getToolTipText() + "-" + brick);
							break;
						case "Bomb":
							button.setIcon( new ImageIcon( getClass().getResource( BOMB_BRICK_PATH)));
							button.setToolTipText( button.getToolTipText() + "-" + brick);
							break;
						case "None":
							button.setIcon( null);
							String x[] = button.getToolTipText().split("-");
							button.setToolTipText( x[0] + "-" + x[1]);
							break;
						}
						
					}
				});
				bricks[i][j] = button;
				panel_1.add( button);
			}
		}
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 663, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	public JButton[][] getBricks() {
		return bricks;
	}

	public void setBricks(JButton[][] bricks) {
		this.bricks = bricks;
	}
	
	
}
