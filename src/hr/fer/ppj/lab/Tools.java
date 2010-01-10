package hr.fer.ppj.lab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java_cup.runtime.Symbol;

import javax.swing.JFrame;
import javax.swing.table.TableModel;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.activity.Activity;
import prefuse.controls.PanControl;
import prefuse.controls.SubtreeDragControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Tree;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class Tools {

	public static TableModel getTableModel(List<Token> listaTokena, Table table) {
		String[][] matrica = new String[listaTokena.size()][5];
		int br=0;
		for(Token t : listaTokena) {
			matrica[br][0] = t.getType().toString(); // tip
			matrica[br][1] = String.valueOf(t.getPointer()); // pointer
			matrica[br][3] = String.valueOf(t.getLine()); // line
			matrica[br][4] = String.valueOf(t.getCol()); // column
			
			// tip
			if(t.getType() == Token.Type.CONST) {
				matrica[br][2] = table.constList.get(t.getPointer()) + " ("
				         + table.constTypeList.get(t.getPointer()).toString() + ")";
			} else if (t.getType() == Token.Type.IDN) {
				matrica[br][2] = table.idnList.get(t.getPointer());
			} else {
				matrica[br][2] = table.krosList.get(t.getPointer());
			}

			br++;
		}
		
		TableModel model = new javax.swing.table.DefaultTableModel(matrica,
            new String [] {
                "Tip", "Pointer", "Vrijednost", "Line", "Column"
        });
		
		return model;
	}
	
	public static Map<String, Integer> symMap = new HashMap<String, Integer>();
	static {
		int[] symList = {
				sym.PLUS, sym.MINUS, sym.MUL, sym.DIV, sym.MOD, sym.AND, sym.OR, sym.ASSINGMENT,
				sym.EQUAL, sym.LESS, sym.GREATER, sym.LEQ, sym.GEQ, sym.NOTEQ, sym.NOT,
				sym.INT, sym.CHAR, sym.FLOAT, sym.BOOLEAN, sym.STRUCT, sym.VOID,
				sym.IF, sym.ELSE,
				sym.DO, sym.WHILE, sym.FOR, sym.BREAK, sym.CONTINUE,
				sym.ZAREZ, sym.TOCKA, sym.TOCKAZAREZ,
				sym.OBLA_L, sym.OBLA_D, sym.UGLATA_L, sym.UGLATA_D, sym.VITICASTA_L, sym.VITICASTA_D,
				sym.RETURN
				};
		String[] valueList = {
				"+", "-", "*", "/", "%", "&&", "||", "=",
				"==", "<", ">", "<=", ">=", "!=", "!",
				"int", "char", "float", "boolean", "struct", "void",
				"if", "else",
				"do", "while", "for", "break", "continue",
				",", ".", ";",
				"(", ")", "[", "]", "{", "}",
				"return"
				};
		for(int i=0; i<valueList.length; i++) {
			symMap.put(valueList[i], symList[i]);
		}
	}
	
	public static int getSym(String value) {
		return symMap.get(value);
	}
	
	public static int getSymForConst(Lexer.ConstType type) {
		if(type == Lexer.ConstType.CHAR) {
			return sym.CONST_CHAR;
		}
		if(type == Lexer.ConstType.FLOAT) {
			return sym.CONST_FLOAT;
		}
		if(type == Lexer.ConstType.INT) {
			return sym.CONST_INT;
		}
		if(type == Lexer.ConstType.STRING) {
			return sym.CONST_STR;
		}
		return -1;
	}
	
	public static int getSymForIdn() {
		return sym.IDN;
	}
	
	public static void addVNode(Graph graph, Node parent, TreeNode child) {
		Node n = graph.addNode();
		n.set("name", child.nodeValue);
		if(child.hasChildren()) {
			n.set("gender", "M");
		} else {
			n.set("gender", "F");
		}
		if(parent!=null) graph.addEdge(parent, n);
		for(int i = 0; i<child.children.length; i++) addVNode(graph, n, child.getChild(i));
	}

	public static void visualizeParseTree(Symbol start) {

		Tree tree = new Tree();
		tree.addColumn("name", String.class);
		tree.addColumn("gender", String.class);
		addVNode(tree, null, (TreeNode)(start.value));
		
        
        // add the graph to the visualization as the data group "graph"
        // nodes and edges are accessible as "graph.nodes" and "graph.edges"
        Visualization vis = new Visualization();
        vis.add("tree", tree);
        vis.setInteractive("graph.edges", null, false);
        
        // -- 3. the renderers and renderer factory ---------------------------
        
        // draw the "name" label for NodeItems
        LabelRenderer r = new LabelRenderer("name");
        r.setRoundedCorner(2, 2); // round the corners
        
        
        // create a new default renderer factory
        // return our name label renderer as the default for all non-EdgeItems
        // includes straight line edges for EdgeItems by default
        vis.setRendererFactory(new DefaultRendererFactory(r));
        
        
        // -- 4. the processing actions ---------------------------------------
        
        // create our nominal color palette
        int[] palette = new int[] {
            ColorLib.rgb(70,182,157), ColorLib.rgb(152,235,175)
        };
        // map nominal data values to colors using our provided palette
        DataColorAction fill = new DataColorAction("tree.nodes", "gender",
                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
        // use black for node text
        ColorAction text = new ColorAction("tree.nodes",
                VisualItem.TEXTCOLOR, ColorLib.gray(0));
        // use light grey for edges
        ColorAction edges = new ColorAction("tree.edges",
                VisualItem.STROKECOLOR, ColorLib.gray(200));
        
        // create an action list containing all color assignments
        ActionList color = new ActionList();
        color.add(fill);
        color.add(text);
        color.add(edges);
        
        // create an action list with an animated layout
        ActionList layout = new ActionList(Activity.DEFAULT_STEP_TIME);
        NodeLinkTreeLayout nltl = new NodeLinkTreeLayout("tree");
        nltl.setOrientation(Constants.ORIENT_TOP_BOTTOM);
        layout.add(nltl);
      //  layout.add(new ForceDirectedLayout("graph"));
        layout.add(new RepaintAction());
        
        // add the actions to the visualization
        vis.putAction("color", color);
        vis.putAction("layout", layout);
        
        
        // -- 5. the display and interactive controls -------------------------
        
        Display d = new Display(vis);
        d.setSize(900, 700); // set display size
        // drag individual items around
        d.addControlListener(new SubtreeDragControl()); // prije DragControl()
        // pan with left-click drag on background
        d.addControlListener(new PanControl()); 
        // zoom with right-click drag
        d.addControlListener(new ZoomControl());
        
        // -- 6. launch the visualization -------------------------------------
        
        // create a new window to hold the visualization
        JFrame frame = new JFrame("Stablo parsiranja");
        // ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(d);
        frame.pack();           // layout components in window
        frame.setVisible(true); // show the window
        
        // assign the colors
        vis.run("color");
        // start up the animated layout
        vis.run("layout");

	}
}
