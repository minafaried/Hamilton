/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lec_task3;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
/**
 *
 * @author Nagla Essam
 */
public class GraphPanel extends Container
{
    static final long serialVersionUID = 420001L;
    DirectedSparseGraph<Integer, Integer> graph = null;
    VisualizationViewer<Integer, Integer> vv = null;
    PickedState<Number> pickedState = null;
    SparseMultigraph<Integer,String> euler = new SparseMultigraph();

    public GraphPanel(Integer[][] nodes_list)
    {
        try
        {
            graph = new DirectedSparseGraph<Integer, Integer>();
            construct_graph(nodes_list);

            vv = new VisualizationViewer<Integer, Integer>
                        (new CircleLayout<Integer, Integer>(graph), new Dimension(400, 400));
 

            // The following code adds capability for mouse picking of
            // vertices/edges. Vertices can even be moved!
        }
        catch (Exception e)
        {
            System.err.println("Failed to construct graph!\n");
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }

    /*Attach the graph panel/container to a specified frame*/
    public void attach_to_frame(JFrame frame)
    {
        frame.setContentPane(vv);
    }

    /*This one should be reimplemented*/
    private void construct_graph(Integer[][] nodes_list)
    {
       
        /*add the nodes*/
        /*for(int i=0; i<nodes_list.length; i++)
        {
            graph.addVertex(i);
          }*/
        int x=0;
        for (int i = 0; i < nodes_list.length; i++) {
            euler.addVertex(i);
			for (int j = 0; j < nodes_list.length; j++) {
				if(nodes_list[i][j]==1) {
					graph.addEdge(x,i, j);
					x++;
				}
			}
		}
    }
}
