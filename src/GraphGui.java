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
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
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
    private void drawRepresentation(Integer[][] representation, String name) {
        GraphPanel graphPanel = new GraphPanel(representation);

        JFrame frame1;
        frame1 = new JFrame(name);
        frame1.setBounds(100, 100, 450, 300);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(43, 41, 175, 124);
        frame1.getContentPane().add(textArea);

        graphPanel.attach_to_frame(frame1);
        frame1.getContentPane().setPreferredSize(new Dimension(640, 480));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);

        iotext.setText(GuiOutput.output);

        GuiOutput.output = "";

    }
    private void draw_Undirected(List<Integer> adjacencyList[])
    {
        SparseMultigraph<Integer, String> euler = new SparseMultigraph();
                int c = 1;
                for (int i = 0; i < adjacencyList.length; i++) {
                    if (adjacencyList[i].size() > 0) {
                        euler.addVertex(i);
                       // euler.degree(i);
                        for (int j = 0; j < adjacencyList[i].size(); j++) {
                            euler.addEdge(Integer.toString(c++), i, adjacencyList[i].get(j));
                        }
                    }
                }

                Layout<Integer, String> layout = new CircleLayout(euler);
                layout.setSize(new Dimension(300, 300)); // sets the initial size of the space
                BasicVisualizationServer<Integer, String> vv
                        = new BasicVisualizationServer<Integer, String>(layout);
                vv.setPreferredSize(new Dimension(350, 350)); //Sets the viewing area size
                vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
                
                JFrame frame = new JFrame("Hamilton Graph");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(vv);
                frame.pack();
                frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(650, 690, 600, 780);
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
        minTreeCheckBox.setBounds(426, 40, 115, 45);
        frame.getContentPane().add(minTreeCheckBox);

        JCheckBox HamiltonPathCheckBox = new JCheckBox("Hamilton Path");
        HamiltonPathCheckBox.setBounds(426, 60, 123, 63);
        frame.getContentPane().add(HamiltonPathCheckBox);
        
        JCheckBox HamiltonCycleCheckBox = new JCheckBox("Hamilton Cycle");
        HamiltonCycleCheckBox.setBounds(426, 93, 130, 73);
        frame.getContentPane().add(HamiltonCycleCheckBox);

        JButton btnGetMatrises = new JButton("get Hamilton");
        btnGetMatrises.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numofvertices = Integer.parseInt(numofverticestext.getText());
                Integer[][] representation = new Integer[numofvertices][numofvertices];
                Integer[][] pathRepresentation = new Integer[numofvertices][numofvertices];
                Integer[][] cycleRepresentation = new Integer[numofvertices][numofvertices];
                Integer[][] minRepresentation = new Integer[numofvertices][numofvertices];
                List<Integer> adjacencyList[];
                List<Edge> edges = new ArrayList<Edge>();
                adjacencyList = new List[numofvertices];
                for (int i = 0; i < numofvertices; i++) {
                    for (int j = 0; j < numofvertices; j++) {
                        representation[i][j] = 0;
                        pathRepresentation[i][j]= 0;
                        cycleRepresentation[i][j]=0;
                        minRepresentation[i][j] = 0;
                    }
                }
                for (int i = 0; i < numofvertices; i++) {
                    adjacencyList[i] = new ArrayList<Integer>();
                }

                int counter = 0;
                boolean d = false;

                int x = 0;
                int first = 0, second = 0, third = 0;
                String[] strTmp = null;
                boolean flag = false;
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
                            /* if (first >= (numofvertices - 1) || second >= (numofvertices - 1)) {
                             showMessageDialog(null, "Invalid Input\n Try again ....");
                             flag = true;
                             //break;
                             }*/
                        } else {
                            break;
                        }
                        /* if (flag == true) {
                         break;
                         } else {*/

                        digraph = digraphCheckBox.isSelected();
                        min = minTreeCheckBox.isSelected();
                        path = HamiltonPathCheckBox.isSelected();
                        cycle = HamiltonCycleCheckBox.isSelected();
                        if (digraph) {
                            counter++;
                            d = false;
                            edges.add(new Edge(first, second, third));
                            adjacencyList[first].add(second);
                            representation[first][second]++;
                        } else {

                            counter++;
                            d = true;
                            edges.add(new Edge(first, second, third));
                            adjacencyList[first].add(second);
                            representation[first][second]++;
                            edges.add(new Edge(second, first, third));
                           // System.out.println(first+" "+second+" "+third);
                           // adjacencyList[second].add(first);
                            representation[second][first]++;
                        }
                    }
                    if (Integer.parseInt(strTmp[0]) == -1) {
                        break;
                    }
                }
               
                if(digraph)
                {
                    drawRepresentation(representation, "Directed Graph");
                }
                else
                {
                    draw_Undirected(adjacencyList);
                }
                g = new Graph(numofvertices, d);
                g.edges = edges;
                Hamilton h = new Hamilton(g);
                if(numofvertices < 7){
                if (min ) {
                    Graph minpath = h.minmumHamiltonCircuit(0);
                    for (int i = 0; i < minpath.representation.length; i++) {
                        for (int j = 0; j < minpath.representation.length; j++) {
                            minRepresentation[i][j] = minpath.representation[i][j];
                            //System.out.print(minRepresentation[i][j]);
                        }
                        //System.out.println();
                    }
                    drawRepresentation(minRepresentation, "Hamilton Minimum Tree");

                }
                }
                else
                {
                  showMessageDialog(null, "We Can't Compute Minimum Tree for this Number of Vertices ..");  
                }
                if (path) {

                    for (int i = 0; i < representation.length; i++) {
                        for (int j = 0; j < representation.length; j++) {
                            g.representation[i][j] = representation[i][j];
                        }
                    }
                    Graph path = h.hamiltonPath();
                    if(path != null){
                    for (int i = 0; i < path.representation.length; i++) {
                        for (int j = 0; j < path.representation.length; j++) {
                            pathRepresentation[i][j] = path.representation[i][j];
                        }
                       
                    }
                    drawRepresentation(pathRepresentation, "Hamilton Path");
                    }
                    else
                    {
                        showMessageDialog(null, "Doesn't have Hamilton Path ..");
                    }
                }
                if(cycle)
                {
                    for (int i = 0; i < representation.length; i++) {
                        for (int j = 0; j < representation.length; j++) {
                            g.representation[i][j] = representation[i][j];
                        }
                    }
                    Graph circuit = h.haminltonCircuit();
                    if(circuit != null){
                    for (int i = 0; i < circuit.representation.length; i++) {
                        for (int j = 0; j < circuit.representation.length; j++) {
                            cycleRepresentation[i][j] = circuit.representation[i][j];
                        }
                       
                    }
                    drawRepresentation(cycleRepresentation, "Hamilton Circuit");
                    }
                    else
                    {
                        showMessageDialog(null, "Doesn't have Hamilton Circuit ..");
                    }
                }
               
            }
        });
        btnGetMatrises.setBounds(150, 670, 127, 20);
        frame.getContentPane().add(btnGetMatrises);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 167, 500, 500);
        frame.getContentPane().add(scrollPane);

        iotext = new JTextArea();
        scrollPane.setViewportView(iotext);

        lblNewLabel = new JLabel("input : vertix1<space>vertix2<space>cost<\\n>at the end put (-1)<\\n>");
        lblNewLabel.setBounds(63, 49, 273, 14);
        frame.getContentPane().add(lblNewLabel);
        lblNewLabel1 = new JLabel("        and at the end put (-1)<\\n>");
        lblNewLabel1.setBounds(73, 55, 300, 25);
        frame.getContentPane().add(lblNewLabel1);

    }
}
