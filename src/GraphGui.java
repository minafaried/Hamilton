/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lec_task3;

/**
 *
 * @author Nagla Essam
 */
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.V;
import static javax.swing.JOptionPane.showMessageDialog;

public class GraphGui {

    private JFrame frame;
    private JTextField numofverticestext;
    int numofvertices = 0;
    JTextArea iotext;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel1;
    boolean digraph = false;
    boolean min = false;
    boolean path = false;
    boolean cycle = false;

    /**
     * Launch the application.
     */
    public static void test() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GraphGui window = new GraphGui();
                    window.initialize();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GraphGui() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void drawRepresentation(Integer[][] representation, Integer[][] costReprestentation, String name) {

        construct_graph(representation, costReprestentation, name);
    }

    private void construct_graph(Integer[][] nodes_list, Integer[][] costReprestentation, String name) {

        int x = 1;

        DirectedSparseGraph<Integer, String> graph = new DirectedSparseGraph<Integer, String>();
        for (int i = 0; i < nodes_list.length; i++) {
            graph.addVertex(i);
            for (int j = 0; j < nodes_list.length; j++) {
                
               // System.out.println("\nCost" + costReprestentation[i][j] );
                if (nodes_list[i][j] == 1) {
                   // System.out.println("Cost"+i + " " +j + costReprestentation[i][j] );
                    String s = "COST " + x + ":" + "(" + costReprestentation[i][j] + ")";
                    graph.addEdge(s, i, j);
                    x++;
                }
            }
        }

        Layout<Integer, String> layout = new CircleLayout(graph);
        layout.setSize(new Dimension(400, 400)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer, String> vv
                = new BasicVisualizationServer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(640, 480)); //Sets the viewing area size
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

    private void draw_Undirected(Integer[][] nodes_list, Integer[][] costReprestentation) {
        SparseMultigraph<Integer, String> graph = new SparseMultigraph();
        int c = 1;
        for (int i = 0; i < nodes_list.length; i++) {
            graph.addVertex(i);
            for (int j = 0; j < nodes_list.length; j++) {
                if (nodes_list[i][j] == 1) {
                    String s = "COST " + c + ":" + "(" + costReprestentation[i][j] + ")";
                    graph.addEdge(s, i, j);
                    c++;
                }
            }
        }

        Layout<Integer, String> layout = new CircleLayout(graph);
        layout.setSize(new Dimension(400, 400)); // sets the initial size of the space
        BasicVisualizationServer<Integer, String> vv
                = new BasicVisualizationServer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(640, 480)); //Sets the viewing area size
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);

    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(450, 150, 600, 539);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        numofverticestext = new JTextField();
        numofverticestext.setBounds(132, 18, 96, 20);
        frame.getContentPane().add(numofverticestext);
        numofverticestext.setColumns(10);

        JLabel lblNumberOfEdges = new JLabel("number of vertices");
        lblNumberOfEdges.setBounds(10, 21, 112, 14);
        frame.getContentPane().add(lblNumberOfEdges);

        JCheckBox digraphCheckBox = new JCheckBox("digraph");
        digraphCheckBox.setBounds(426, 20, 97, 23);
        frame.getContentPane().add(digraphCheckBox);

        JCheckBox minTreeCheckBox = new JCheckBox("Min Hamilton");
        minTreeCheckBox.setBounds(426, 46, 115, 20);
        frame.getContentPane().add(minTreeCheckBox);

        JCheckBox HamiltonPathCheckBox = new JCheckBox("Hamilton Path");
        HamiltonPathCheckBox.setBounds(426, 69, 123, 30);
        frame.getContentPane().add(HamiltonPathCheckBox);

        JCheckBox HamiltonCycleCheckBox = new JCheckBox("Hamilton Cycle");
        HamiltonCycleCheckBox.setBounds(426, 102, 130, 23);
        frame.getContentPane().add(HamiltonCycleCheckBox);

        JButton btnGetMatrises = new JButton("get Hamilton");

        btnGetMatrises.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numofvertices = Integer.parseInt(numofverticestext.getText());
                Integer[][] representation = new Integer[numofvertices][numofvertices];
                Integer[][] costReprestentation = new Integer[numofvertices][numofvertices];
                Integer[][] pathRepresentation = new Integer[numofvertices][numofvertices];
                Integer[][] cycleRepresentation = new Integer[numofvertices][numofvertices];
                Integer[][] minRepresentation = new Integer[numofvertices][numofvertices];
                List<Integer> adjacencyList[];
                List<Edge> edges = new ArrayList<Edge>();
                adjacencyList = new List[numofvertices];
                for (int i = 0; i < numofvertices; i++) {
                    for (int j = 0; j < numofvertices; j++) {
                        representation[i][j] = 0;
                        pathRepresentation[i][j] = 0;
                        cycleRepresentation[i][j] = 0;
                        minRepresentation[i][j] = 0;
                        costReprestentation[i][j] = 0;
                    }
                }
                for (int i = 0; i < numofvertices; i++) {
                    adjacencyList[i] = new ArrayList<Integer>();
                }

                boolean d = false;

                int first = 0, second = 0, third = 0;
                String[] strTmp = null;
                Graph g;
                while (true) {
                    String[] tmp = iotext.getText().split("\n");
                    String str = "";
                    for (String a : tmp) {
                        str += a;
                        str += " ";
                        strTmp = str.split(" ");
                        str = "";

                        if (Integer.parseInt(strTmp[0]) != -1) {
                            first = Integer.parseInt(strTmp[0]);
                            second = Integer.parseInt(strTmp[1]);
                            third = Integer.parseInt(strTmp[2]);
                        } else {
                            break;
                        }
                        digraph = digraphCheckBox.isSelected();
                        min = minTreeCheckBox.isSelected();
                        path = HamiltonPathCheckBox.isSelected();
                        cycle = HamiltonCycleCheckBox.isSelected();
                        if (digraph) {
                            d = false;
                            edges.add(new Edge(first, second, third));
                            adjacencyList[first].add(second);
                            representation[first][second]++;
                            costReprestentation[first][second] = third;
                        } else {
                            d = true;
                            edges.add(new Edge(first, second, third));
                            adjacencyList[first].add(second);
                            representation[first][second]++;
                            edges.add(new Edge(second, first, third));
                           // System.out.println(first+" "+second+" "+third);
                            // adjacencyList[second].add(first);
                            //representation[second][first]++;
                            costReprestentation[first][second] = third;
                            costReprestentation[second][first] = third;
                        }
                    }
                    if (Integer.parseInt(strTmp[0]) == -1) {
                        break;
                    }
                }

                if (digraph) {

                    drawRepresentation(representation, costReprestentation, "Directed Graph");

                } else {
                    draw_Undirected(representation, costReprestentation);
                }
                g = new Graph(numofvertices, d);
                g.edges = edges;
                Hamilton h = new Hamilton(g);
                if (numofvertices < 7) {
                    if (min) {
                        Graph minpath = h.minmumHamiltonCircuit(0);
                        GuiOutput.output += "minmumHamiltonCircuit is :\n";
                        int totalcost = 0;
                        for (int i = 0; i < minpath.edges.size(); i++) {
                            totalcost += minpath.getEdgeCost(minpath.edges.get(i).first, minpath.edges.get(i).second);
                            GuiOutput.output += "first :" + minpath.edges.get(i).first + " second :"
                                    + minpath.edges.get(i).second + " cost :" + minpath.edges.get(i).cost + "\n";
                        }
                        GuiOutput.output += "total cost is :" + totalcost + "\n";
                        
                        for (int i = 0; i < minpath.representation.length; i++) {
                            for (int j = 0; j < minpath.representation.length; j++) {
                                //if(minpath.representation[i][j] == re)
                                minRepresentation[i][j] = minpath.representation[i][j];
                               // System.out.println("i"+i +"j"+ j + " "+minRepresentation[i][j]);
                            }
                            //System.out.println();
                        }
                      //  Integer[][] costReprestentation1 = new Integer[numofvertices][numofvertices];
                        /*for(int i = 0 ; i < costReprestentation.length;i++)
                        {
                            for(int j = 0 ; j < costReprestentation.length;j++)
                            {
                                System.out.println("i"+i +"j"+ j + " "+costReprestentation[i][j]);
                            }
                        }*/
                        drawRepresentation(minRepresentation, costReprestentation, "Hamilton Minimum Tree");

                    }
                } else {
                    GuiOutput.output += "We Can't Compute Minimum Tree for this Number of Vertices ..\n";
                    showMessageDialog(null, "We Can't Compute Minimum Tree for this Number of Vertices ..");
                }
                if (path) {

                    for (int i = 0; i < representation.length; i++) {
                        for (int j = 0; j < representation.length; j++) {
                            g.representation[i][j] = representation[i][j];
                        }
                    }
                    Graph path = h.hamiltonPath();
                    GuiOutput.output += "hamiltonPath is :\n";

                    if (path != null) {
                        for (int i = 0; i < path.edges.size(); i++) {
                            GuiOutput.output += "first :" + path.edges.get(i).first + " second :"
                                    + path.edges.get(i).second + "\n";
                        }
                        for (int i = 0; i < path.representation.length; i++) {
                            for (int j = 0; j < path.representation.length; j++) {
                                pathRepresentation[i][j] = path.representation[i][j];
                            }

                        }

                        drawRepresentation(pathRepresentation, costReprestentation, "Hamilton Path");
                    } else {
                        GuiOutput.output += "Doesn't have Hamilton Path ..\n";
                        showMessageDialog(null, "Doesn't have Hamilton Path ..");
                    }
                }
                if (cycle) {
                    for (int i = 0; i < representation.length; i++) {
                        for (int j = 0; j < representation.length; j++) {
                            g.representation[i][j] = representation[i][j];
                        }
                    }

                    Graph circuit = h.haminltonCircuit();
                    GuiOutput.output += "hamiltonCircuit is :\n";
                    if (circuit != null) {
                        for (int i = 0; i < circuit.edges.size(); i++) {
                            GuiOutput.output += "first :" + circuit.edges.get(i).first + " second :"
                                    + circuit.edges.get(i).second + "\n";
                        }
                        for (int i = 0; i < circuit.representation.length; i++) {
                            for (int j = 0; j < circuit.representation.length; j++) {
                                cycleRepresentation[i][j] = circuit.representation[i][j];
                            }

                        }
                        drawRepresentation(cycleRepresentation, costReprestentation, "Hamilton Circuit");
                    } else {
                        GuiOutput.output += "Doesn't have Hamilton Circuit ..\n";
                        showMessageDialog(null, "Doesn't have Hamilton Circuit ..");
                    }
                }
                iotext.setText(GuiOutput.output);
            }

        });
        btnGetMatrises.setBounds(213, 459, 127, 20);
        frame.getContentPane().add(btnGetMatrises);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 167, 500, 266);
        frame.getContentPane().add(scrollPane);

        iotext = new JTextArea();
        scrollPane.setViewportView(iotext);

        lblNewLabel = new JLabel("input : vertix1<space>vertix2<space>cost<\\n>at the end put (-1)<\\n>");
        lblNewLabel.setBounds(48, 122, 273, 14);
        frame.getContentPane().add(lblNewLabel);
        lblNewLabel1 = new JLabel("        and at the end put (-1)<\\n>");
        lblNewLabel1.setBounds(66, 131, 300, 25);
        frame.getContentPane().add(lblNewLabel1);

    }
}

