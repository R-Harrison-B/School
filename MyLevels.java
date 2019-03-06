
public class MyLevels {
    /**
     * Character values for displaying the different statuses of the game board cells.
     */
    public static final char EMPTY_CHAR = ' '; // Empty character
    public static final char BOX_CHAR = '='; // Box character
    public static final char WALL_CHAR = '#'; // Wall character
    public static final char WORKER_CHAR = '@'; // Worker
    public static final char GOAL_CHAR = '.'; // Goal character
    public static final char BOX_GOAL_CHAR = '*'; // Box on a goal character
    public static final char WORK_GOAL_CHAR = '+'; // Worker on a goal character



    public static final char[][][] LEVELS = {
//      {{.,  ,  }, 
//      {@, =,  },
//      { ,  ,  }},
           {{GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR}, 
            {WORKER_CHAR, BOX_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR}},
           
//         {{., .,  ,  ,  ,  },
//         { ,  ,  ,  ,  ,  },
//         { ,  ,  ,  ,  ,  },
//         { ,  ,  ,  , =,  },
//         { ,  ,  ,  , @,  },
//         { ,  ,  ,  , =,  }},
           {{GOAL_CHAR, GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR}},
        
        //  {{., ., .,  ,  ,  ,  },
        //   { ,  ,  ,  ,  ,  ,  , ,  ,  },
        //   { ,  ,  ,  ,  ,  ,  , ,  ,  ,  ,  ,  },
         //  { ,  ,  ,  ,  ,  ,  , ,  ,  ,  ,  ,  },
         //  { ,  ,  ,  ,  , =,  , ,  ,  },
        //   { ,  ,  ,  ,  , =,  , ,  ,  },
         //  { ,  , @,  ,  , =,  , ,  ,  },
         //  { ,  ,  ,  ,  ,  ,  , ,  ,  },
        //   { ,  ,  ,  ,  ,  ,  , ,  ,  },
        //   { ,  ,  ,  ,  ,  ,  , ,  ,  }
           {{GOAL_CHAR, GOAL_CHAR, GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR}}};


    public static final int[][] GOALS = {{0, 0}, {0, 0, 0, 1}, {0, 0, 0, 1, 0, 2}};


    public static final char UP_CHAR = '8'; // move worker up
    public static final char DOWN_CHAR = '2';// move worker down
    public static final char LEFT_CHAR = '9';// move worker left
    public static final char RIGHT_CHAR = '6';// move worker right
    public static final char QUIT_CHAR = 'q';// quit game


}


