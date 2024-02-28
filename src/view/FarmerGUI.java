package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import puzzles.FarmerWolfGoatSide;

/**
 * @author valyis@nyf.hu
 */
enum Method {DEPTHFIRST, BREADTHFIRST, BESTFIRST, BACKTRACK};


public class FarmerGUI
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               TextComponentFrame frame = new TextComponentFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

/**
 * A frame with sample text components.
 */
class TextComponentFrame extends JFrame
{
	private static final long serialVersionUID = -7240950346247779616L;
    Method method = Method.DEPTHFIRST;
    puzzles.FarmerWolfGoatSide farmer=puzzles.FarmerWolfGoatSide.EAST;
    puzzles.FarmerWolfGoatSide wolf=puzzles.FarmerWolfGoatSide.EAST;
    puzzles.FarmerWolfGoatSide goat=puzzles.FarmerWolfGoatSide.EAST;
    puzzles.FarmerWolfGoatSide cabbage=puzzles.FarmerWolfGoatSide.EAST;

	
public TextComponentFrame()
   {
      setTitle("Farmer, kecske, káposzta és farkas probléma megoldóprogramja");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      
      JPanel northPanel = new JPanel();
      northPanel.setLayout(new GridBagLayout());
      
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.weightx = 0.0;
      constraints.weighty = 0.0;
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 4;
      constraints.gridheight = 1;
      northPanel.add(new JLabel("Kereső beállítása. Backtrack-et ne alkalmazzon olyan problémára, amely visszaléphet uganazon állapotba!"),constraints);
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(1,4));
      final ButtonGroup buttonGroup = new ButtonGroup();
      final JRadioButton depthFirstButton = new JRadioButton("Mélységi", true);
      buttonPanel.add(depthFirstButton);
      final JRadioButton breadthFirstButton = new JRadioButton("Szélességi");
      buttonPanel.add(breadthFirstButton);
      final JRadioButton bestFirstButton = new JRadioButton("Best-First");
      buttonPanel.add(bestFirstButton);
      final JRadioButton backtrackButton = new JRadioButton("Backtrack");
      buttonGroup.add(depthFirstButton);
      buttonGroup.add(breadthFirstButton);
      buttonGroup.add(bestFirstButton);
      buttonGroup.add(backtrackButton);
      constraints.gridy=1;
      constraints.gridx=0;    
      constraints.gridwidth = 4;
      constraints.gridheight = 2;
      northPanel.add(buttonPanel, constraints);
	  depthFirstButton.addActionListener(	  new ActionListener()
      {
	         public void actionPerformed(ActionEvent event)
	         { method = Method.DEPTHFIRST;
	         }
	      }
);
	  breadthFirstButton.addActionListener(	  new ActionListener()
      {
	         public void actionPerformed(ActionEvent event)
	         { method = Method.BREADTHFIRST;
	         }
	      }
);
	  bestFirstButton.addActionListener(	  new ActionListener()
      {
	         public void actionPerformed(ActionEvent event)
	         { method = Method.BESTFIRST;
	         }
	      }
  		  );
	  backtrackButton.addActionListener(	  new ActionListener()
      {
	         public void actionPerformed(ActionEvent event)
	         { method = Method.BACKTRACK;
	         }
	      }
  		  );

	  
	  
	  
      constraints.gridx = 0;      
      constraints.gridy = 3;
      constraints.gridwidth = 4;
      northPanel.add(new JLabel("Állítsa be a kezdő pozíciókat!",SwingConstants.CENTER), constraints);

      
      JComponent inputTable = new JPanel();
      inputTable.setLayout(new GridLayout(2,4));
      inputTable.add(new JLabel("A farmer", SwingConstants.CENTER));
      inputTable.add(new JLabel("A farkas", SwingConstants.CENTER));
      inputTable.add(new JLabel("A kecske", SwingConstants.CENTER));
      inputTable.add(new JLabel("A káposzta", SwingConstants.CENTER));
      final JComboBox farmerField = new JComboBox();
      farmerField.addItem("EAST");
      farmerField.addItem("WEST");
      farmerField.setEditable(true);
      farmerField.addActionListener(
    		  new ActionListener()
    		  {
    			  public void actionPerformed(ActionEvent action)
    			  {
    				  farmer  = FarmerWolfGoatSide.valueOf((String)(farmerField.getSelectedItem()));
    			  }
    		  }
    		  );
      final JComboBox wolfField = new JComboBox();
      wolfField.addItem("EAST");
      wolfField.addItem("WEST");
      wolfField.setEditable(true);
      wolfField.addActionListener(
    		  new ActionListener()
    		  {
    			  public void actionPerformed(ActionEvent action)
    			  {
    				  wolf  = FarmerWolfGoatSide.valueOf((String)(wolfField.getSelectedItem()));
    			  }
    		  }
    		  );
      final JComboBox goatField = new JComboBox();
      goatField.addItem("EAST");
      goatField.addItem("WEST");
      goatField.setEditable(true);
      goatField.addActionListener(
    		  new ActionListener()
    		  {
    			  public void actionPerformed(ActionEvent action)
    			  {
    				  goat  = FarmerWolfGoatSide.valueOf((String)(goatField.getSelectedItem()));
    			  }
    		  }
    		  );
      final JComboBox cabbageField = new JComboBox();
      cabbageField.addItem("EAST");
      cabbageField.addItem("WEST");
      cabbageField.setEditable(true);
      cabbageField.addActionListener(
    		  new ActionListener()
    		  {
    			  public void actionPerformed(ActionEvent action)
    			  {
    				  cabbage  = FarmerWolfGoatSide.valueOf((String)(cabbageField.getSelectedItem()));
    			  }
    		  }
    		  );

      inputTable.add(farmerField);
      inputTable.add(wolfField);
      inputTable.add(goatField);
      inputTable.add(cabbageField);
      
      
      
      constraints.gridy=5;
      constraints.gridwidth=4;
      constraints.gridx=0;
      northPanel.add(inputTable, constraints);
 
      add(northPanel, BorderLayout.NORTH);
      
      final JTextArea textArea = new JTextArea(8, 40);
      JScrollPane scrollPane = new JScrollPane(textArea);

      add(scrollPane, BorderLayout.CENTER);

      // add button to append text into the text area

      JPanel southPanel = new JPanel();

      JButton megoldButton = new JButton("Megold");
      southPanel.add(megoldButton);
      megoldButton.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	
               puzzles.FarmerWolfGoatState fwgcState = new puzzles.FarmerWolfGoatState(null, farmer, wolf, goat, cabbage);
               search.Solver solver = null;
               switch (method) {
                   case DEPTHFIRST: solver = new search.DepthFirstSolver(); break;
                   case BREADTHFIRST: solver = new search.BreadthFirstSolver(); break;
                   case BESTFIRST: solver = new search.BestFirstSolver(); break;
                   case BACKTRACK: solver= new search.BackTrackSolver(); break;
               }
               textArea.selectAll();
               textArea.replaceSelection("");
               textArea.append("method: "+method.toString()+"\n"+
            "farmer: "+farmer+"\n"+
            "wolf"+wolf+"\n"+
            "g: "+goat+"\n"+
            "cab: "+cabbage+"\n"            
            		   );
               textArea.append(solver.solve(fwgcState).toString());
               
               
            }
         });

      add(southPanel, BorderLayout.SOUTH);

      // add a text area with scroll bars

   }

   public static final int DEFAULT_WIDTH = 700;
   public static final int DEFAULT_HEIGHT = 600;
}