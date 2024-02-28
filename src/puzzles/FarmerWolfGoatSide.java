package puzzles;

        /* Enum, ami a lehetséges két oldalát ábrázolja a folyónak. */

	public enum FarmerWolfGoatSide {
        EAST { public FarmerWolfGoatSide getOpposite() { return WEST; } },
        WEST { public FarmerWolfGoatSide getOpposite() { return EAST; } };
        
        abstract public FarmerWolfGoatSide getOpposite();
    }
