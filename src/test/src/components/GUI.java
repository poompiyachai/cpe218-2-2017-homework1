

package components;
import static java.lang.Character.getNumericValue;
import java.util.Stack;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;



public class GUI extends JPanel
                      implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    static DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("");
    private URL helpURL;
    private static boolean DEBUG = false;
      public static String v= "";
     
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public GUI() {
        super(new GridLayout(1,0));

        
        //Create the nodes.
        
        Node w = new Node();
        w = tree(v);
        createNodes(top,w);
       top = top.getNextNode();
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
        ImageIcon nodeIcon = createImageIcon("middle.gif");
    if (nodeIcon != null) {
            DefaultTreeCellRenderer renderer =
                    new DefaultTreeCellRenderer();
            renderer.setClosedIcon(nodeIcon);
            renderer.setOpenIcon(nodeIcon);
            tree.setCellRenderer(renderer);
        }
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();
        
        htmlPane.setText(inor(node));
       
        
    }

    private static class Homework1 {

       
    }

    private class BookInfo {
        public String bookName;
        public URL bookURL;

        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                                   + filename);
            }
        }

        public String toString() {
            return bookName;
        }
    }

    private void initHelp() {
        String s = "TreeDemoHelp.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }
      
    }

   
        
        
        
        
        
        
        
        
        
    

    private void createNodes(DefaultMutableTreeNode top,Node w) {
        
          
        if(w.left.left!=null&&w.right.right!=null)
        {
            DefaultMutableTreeNode category = null;
   
        category = new DefaultMutableTreeNode(""+w.data);
        
           top.add(category);
        
        
               
         createNodes(category,w.left);
        createNodes(category,w.right);
        
           
            
        
        }
        else if(w.left.left!=null)
        {
             DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
        category = new DefaultMutableTreeNode(""+w.data);
                        top.add(category);

            createNodes(category,w.left);
              book = new DefaultMutableTreeNode(new BookInfo
            (""+w.right.data,
            "tutorial.html"));
        category.add(book);
        }
        else if(w.right.right!=null)
        {
             DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
        category = new DefaultMutableTreeNode(""+w.data);
                        top.add(category);

           
              book = new DefaultMutableTreeNode(new BookInfo
            (""+w.left.data,
            "tutorial.html"));
        category.add(book);
         createNodes(category,w.right);
        
        }
        else
        {
            DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
           category = new DefaultMutableTreeNode(""+w.data);
           
                top.add(category);
        book = new DefaultMutableTreeNode(new BookInfo
            (""+w.left.data,
            "tutorial.html"));
        category.add(book);
            
        
            
           
                book = new DefaultMutableTreeNode(new BookInfo
            (""+w.right.data,
            "tutorial.html"));
        category.add(book); 
        }
       
        
        
       
    }
        
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
    
    
    public static Node tree(String input)
     {
         Stack<Node> keepnode = new Stack<>();
         
         char[] b = input.toCharArray();
         
         
         for(int i =0;i<b.length;i++)
        {
             Node z = new Node();
             Node z1 = new Node();
             Node z2 = new Node();
            if(b[i]!='+'&&b[i]!='-'&&b[i]!='*'&&b[i]!='/')
            {
                z.data = b[i];
                keepnode.push(z);
               
            }
            else 
            {z.data = b[i];
                z1 = keepnode.pop();
                z2 = keepnode.pop();
                z.right = z1;
                z.left = z2;
                z1.parent = z;
                z2.parent = z;
                keepnode.push(z);
            }
        }
         return keepnode.pop();
     }
     
     
     
     
     public static String inorder(Node input)
    {
                
            if((input.left.data=='+'||input.left.data=='-'||input.left.data=='*'||input.left.data=='/')&&(input.right.data=='+'||input.right.data=='-'||input.right.data=='*'||input.right.data=='/'))
        {
            String x = inorder(input.left) + input.data + inorder(input.right);
          return x;
             
             
            
        }
            else if(input.left.data=='+'||input.left.data=='-'||input.left.data=='*'||input.left.data=='/')
            {
                String x = "("+inorder(input.left) + input.data + ""+input.right.data+")";
                return x;
            }
            else if(input.right.data=='+'||input.right.data=='-'||input.right.data=='*'||input.right.data=='/')
            {
                String x = "("+input.left.data+"" + input.data + inorder(input.right)+")";
                return x;
            }
        
        
            else
        {
          return "("+input.left.data + input.data + input.right.data+")" ;
        }
    }




public static int calculate(Node input)
     {
        
         if((input.left.data=='+'||input.left.data=='-'||input.left.data=='*'||input.left.data=='/')&&(input.right.data=='+'||input.right.data=='-'||input.right.data=='*'||input.right.data=='/'))
        {
            int x = sum(calculate(input.left),calculate(input.right),input.data);
          return x;
             
             
            
        }
            else if(input.left.data=='+'||input.left.data=='-'||input.left.data=='*'||input.left.data=='/')
            {
                
                int x = sum(calculate(input.left), getNumericValue(input.right.data),input.data);
                return x;
            }
            else if(input.right.data=='+'||input.right.data=='-'||input.right.data=='*'||input.right.data=='/')
            {
                
                int x = sum( getNumericValue(input.left.data),calculate(input.right),input.data);
                return x;
            }
        
        
            else
        {
           
          return sum( getNumericValue(input.left.data), getNumericValue(input.right.data),input.data) ;
        }
         
     }




public static int sum(int q,int w,char c)
{
   
    if(c == '+')
    {
        
       return q+w;
    }
    else if(c == '-')
    {
        
        return q-w;
    }
    else if(c =='*')
    {
        
        return q*w;
    }
    else 
    {
        return q/w;
    }
}


public static String inor(DefaultMutableTreeNode input)
{
    
     
	if(input.getChildAt(0).getChildCount()==2&&input.getChildAt(1).getChildCount()==2)
	{ 
            
	    	String   a = inor((DefaultMutableTreeNode)input.getChildAt(0))+((DefaultMutableTreeNode)input).toString()+inor((DefaultMutableTreeNode)input.getChildAt(1));
	      
	      return a;
	}
	else if(input.getChildAt(0).getChildCount()==2)
	{
		String a;
	      if((DefaultMutableTreeNode) input.getParent()!=null)
	      {
	    	   a = "("+inor((DefaultMutableTreeNode)input.getChildAt(0))+((DefaultMutableTreeNode)input).toString()+((DefaultMutableTreeNode)input.getChildAt(1)).toString()+")";
	      }
	      else
	      {
	    	   a = inor((DefaultMutableTreeNode)input.getChildAt(0))+((DefaultMutableTreeNode)input).toString()+((DefaultMutableTreeNode)input.getChildAt(1)).toString();
	      }
	      return a;
	}
	else if(input.getChildAt(1).getChildCount()==2)
	{
		String a;
	      if((DefaultMutableTreeNode) input.getParent()!=null)
	      {
	    	   a = "("+((DefaultMutableTreeNode)input.getChildAt(0)).toString()+((DefaultMutableTreeNode)input).toString()+inor((DefaultMutableTreeNode)input.getChildAt(1))+")";
	      }
	      else
	      {
	    	   a = ((DefaultMutableTreeNode)input.getChildAt(0)).toString()+((DefaultMutableTreeNode)input).toString()+inor((DefaultMutableTreeNode)input.getChildAt(1));
	      }
	      return a;
	}
	else 
	{String b ;
		if((DefaultMutableTreeNode) input.getParent()!=null)
		{
			b =  "("+((DefaultMutableTreeNode)input.getChildAt(0)).toString()+((DefaultMutableTreeNode)input).toString()+((DefaultMutableTreeNode)input.getChildAt(1)).toString()+")";
		}
		else
		{
			b =  ""+((DefaultMutableTreeNode)input.getChildAt(0)).toString()+((DefaultMutableTreeNode)input).toString()+((DefaultMutableTreeNode)input.getChildAt(1)).toString()+"";
		}
		return b;
	}
	
	
	
	
}
	

protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Homework1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }









public static String infix(Node input)
{
    
     
	if(input.left.left!=null&&input.right.right!=null)
	{ 
            ;
		 String a;
	      if(input.parent!=null)
	      {
	    	   a = "("+infix(input.left)+input.data+infix(input.right)+")";
	      }
	      else
	      {
	    	   a = infix(input.left)+input.data+infix(input.right);
	      }
	      return a;
	}
	else if(input.left.left!=null)
	{
		String a;
	      if(input.parent!=null)
	      {
	    	   a = "("+infix(input.left)+input.data+input.right.data+")";
	      }
	      else
	      {
	    	   a = infix(input.left)+input.data+input.right.data;
	      }
	      return a;
	}
	else if(input.right.right!=null)
	{
		String a;
	      if(input.parent!=null)
	      {
	    	   a = "("+input.left.data+input.data+infix(input.right)+")";
	      }
	      else
	      {
	    	   a = input.left.data+input.data+infix(input.left);
	      }
	      return a;
	}
	else 
	{String b ;
		if(input.parent!=null)
		{
			b =  "("+input.left.data+input.data+input.right.data+")";
		}
		else
		{
			b =  ""+input.left.data+input.data+input.right.data+"";
		}
		return b;
	}
	
	
	
	
}

public static void main(String[] args) {
    String w = "532+-35++";
 
   if(args.length>0)
   {
      w = args[0];
   }
      v = w;
   System.out.print(inorder(tree(w)));
 System.out.println(" = "+calculate(tree(w)));
   
   javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
           createAndShowGUI();
       }
   });
}


}



/*

	
*/