package Step_semester_3.src.main.java.week4;

class RouteRanking {

    private String routeCode;
    private String routeName;
    private int priority;

    public RouteRanking(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public RouteRanking(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(RouteRanking other) {

        // First compare priority
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }

        // Then compare route code ignoring case
        int codeCompare =
                this.routeCode.compareToIgnoreCase(other.routeCode);

        if (codeCompare != 0) {
            return codeCompare;
        }

        // Finally compare route name
        return this.routeName.compareTo(other.routeName);
    }

    public static RouteRanking[] rankRoutes(RouteRanking[] routes) {

        // Bubble sort
        for (int i = 0; i < routes.length - 1; i++) {

            for (int j = 0; j < routes.length - i - 1; j++) {

                if (routes[j].compareTo(routes[j + 1]) > 0) {

                    RouteRanking temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }

        return routes;
    }

    public static void main(String[] args) {

        RouteRanking[] routes = {
                new RouteRanking("RT205L", "Airport Express", 3),
                new RouteRanking("rt201j", "City Central", 4),
                new RouteRanking("RT299T", "Night Service")
        };

        rankRoutes(routes);

        for (RouteRanking r : routes) {
            System.out.println(r.routeCode);
        }
    }
}
