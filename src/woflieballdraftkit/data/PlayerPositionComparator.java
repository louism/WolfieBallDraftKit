/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woflieballdraftkit.data;

import java.util.Comparator;

/**
 *
 * @author louis
 */
public class PlayerPositionComparator implements Comparator
{

    @Override
    public int compare(Object o1, Object o2) 
    {
        Player p1 = (Player) o1;
        Player p2 = (Player) o2;
        
        if(p1.getValue() < p2.getValue())
        {
            return -1;
        }
        else if (p1.getValue() == p2.getValue())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    
}
