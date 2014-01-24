package com.tsp;

public class LocaleProjection {

    /**
     * Rayon de la terre en metre
     */
    private static final float EARTH_RADIUS = 6378137;

    /**
     * Donne les longitude et latitude du centre de projection pour l'ensemble
     * des runs pass�s en param�tres
     * 
     * @param pRuns Liste des runs
     * @return un tableau de 2 valeurs : [Cx,Cy]
     * @throws DataAccessException
     */
    public static double[] getProjectionCenter(double[][] points) {
    	double[] center = new double[3];
        
    	double xMoy = 0;
    	double yMoy =  0;
    	double zMoy =  0;
    	for (double[] point : points) {
    		xMoy += point[0];
    		yMoy += point[1];
    		zMoy += point[2];
    	}
        center[0] = xMoy / points.length;
        center[1] = yMoy / points.length;
        center[2] = zMoy / points.length;
        return center;
    }
 

    /*
     * (non-Javadoc)
     * @see com.xtl.tsp.projection.Projection#projectionSimple(com.xtl.tsp.model.Run)
     */
    public static double[][] projectionSimple(double[] center, double[][] points) {
  
        // on centre les points par rapport au centre de projection
        double[][] centeredPts = centerPoint(center[2], center[1], center[0], points);

        // on effectue la projection
        return doProjection(centeredPts);
        
    }
    

    /**
     * Centre les points du run par rapport au centre de projection locale
     * 
     * @author sgeorgoux
     * @param pCenterLatitude
     *            la latitude du centre de projection
     * @param pCenterLongitude
     *            la longitude du centre de projection
     * @param pPoints
     *            la list des points
     * @return une TreeMap avec :<br>
     *         cl� : l'id du point <br>
     *         valeur : un tableau de 2 �l�ment [pXcentr�, pYcentr�]
     */
    private static double[][] centerPoint(double pCenterAltitude, double pCenterLatitude, double pCenterLongitude, double[][] pPoints) {
        double[][] centerCoords = new double[pPoints.length][];
        
    	double[] coord;
        double xCentered; // x centré
        double yCentered; // y centré
        double zCentered;
        
        int i = 0;
        for( double[] pt : pPoints ) 
        {
            xCentered = pt[1] - pCenterLongitude;
            yCentered = pt[0] - pCenterLatitude;
            zCentered = pt[2] - pCenterAltitude;
            coord = new double[3];
            coord[0] = xCentered;
            coord[1] = yCentered;
            coord[2] = zCentered;
            
            centerCoords[i] = coord;
            i++;
        }

        return centerCoords;
    }

    /**
     * Effectue la projection locale pour chaque point du run :<br>
     * 1. Convertit les longitudes et latitude du run, exprim�es en dms, en
     * m�tre <br>
     * 2. Met � jour le run pass� en param�tre
     * 
     * @author ykouira
     * @param pPoints
     * @param pCenteredPt
     *            la liste des points en dms centr� selon le centre de
     *            projection locale
     */
    private static double[][] doProjection(double[][] pCenteredPt) {
    	
    	double[][] points = new double[pCenteredPt.length][];
    	
        double latM;// latitude en metre
        double longM; // longitude en metre
        double altM;
        
        int i = 0;
        
        // pour chaque point centre, on calcule ses coordon�es en metre
        // et on met � jour son point
        for (double[] coord : pCenteredPt) 
        {
            // calcul des coordonees en metre
            longM = (float) convertLongitude(coord[0], coord[1] );            
            latM = (float) convertLatitude(coord[1]);
            altM = coord[2];
            
            points[i] = new double[] {longM, latM, altM};
            i++;
        }
        
        return points;
    }
    

    /**
     * Convertit la latitude(dms) en m�tre selon le plan de projection locale
     * 
     * @author sgeorgoux
     * @param pLatitude
     * @return la latitude en m�tre
     */
    private static double convertLatitude(double pLatitude) {
        double lat = Math.toRadians(pLatitude);

        return EARTH_RADIUS * Math.tan(lat);
    }

    /**
     * Convertit la Longitude(dms) en m�tre selon le plan de projection locale
     * 
     * @author sgeorgoux
     * @param pLongitude
     * @param pLatitude
     * @return la Longitude en m�tre
     */
    private static double convertLongitude(double pLongitude, double pLatitude) 
    {
        double lat = Math.toRadians(pLatitude);
        double lon = Math.toRadians(pLongitude);
        return EARTH_RADIUS * Math.tan(lon)*Math.cos(lat);
        
        //return EARTH_RADIUS * Math.tan(tmp);
    }

}
