//function that takes a string and runs some predetermine sanitzation and returns the cleaned version of the string
def String cleanThisString(String stringToClean){
    Map<String, String> map = new HashMap<String, String>(); //create a Hashmap to record our string sanitizing 
    map.put("&","&amp;");
    map.put(">","&gt;");
    map.put("<","&lt;");
    map.put("\'","&apos;");
    for (String key : map.keySet()){ //iterate over the hashmap key and apply the function of stringClean to replace and sanitize our string
        stringToClean = stringToClean.replaceAll(key,map.get(key));
        }
    return stringToClean;
    }
//Calculates the difference between start and end time and checks if the value exists if not returns null. Outputs a string with, " mins" attached
def String getDateDiff(Date start, Date end) {
    if (start != null && end != null){
            long diffInMillies = end.getTime() - start.getTime();
            long incidentDuration;
        incidentDuration = TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS);
        return incidentDuration + " mins";   
    }
    return " ";
}
