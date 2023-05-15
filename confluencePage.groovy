import com.atlassian.applinks.api.ApplicationLink
import com.atlassian.applinks.api.ApplicationLinkService
import com.atlassian.applinks.api.application.confluence.ConfluenceApplicationType
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.sal.api.component.ComponentLocator
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.config.properties.APKeys
import com.atlassian.sal.api.net.Request
import com.atlassian.sal.api.net.Response
import com.atlassian.sal.api.net.ResponseException
import com.atlassian.sal.api.net.ResponseHandler
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import com.atlassian.jira.issue.link.RemoteIssueLinkManager
import java.util.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


 
def ApplicationLink getPrimaryConfluenceLink() {
    def applicationLinkService = ComponentLocator.getComponent(ApplicationLinkService)
    final ApplicationLink conflLink = applicationLinkService.getPrimaryApplicationLink(ConfluenceApplicationType)
    conflLink
}
 
// the issue provided in the binding
Issue issue = issue
 
// check if there is already a confluence link attached
def remoteIssueLinkManager = ComponentAccessor.getComponent(RemoteIssueLinkManager)
def remoteLinks = remoteIssueLinkManager.getRemoteIssueLinksForIssue(issue)
if (remoteLinks == null){
    return
}


// function that calculates the incident duration
public static long getDateDiff(Date start, Date end, TimeUnit timeUnit) {
    long diffInMillies = end.getTime() - start.getTime();
    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
}


//Get custom field values: incident start time and incident end time
def customFieldManager = ComponentAccessor.getCustomFieldManager()
//incident start time
def cField1 = customFieldManager.getCustomFieldObjectByName("Incident Start Time")
 Date incidentStart = issue.getCustomFieldValue(cField1)


// incident end time
def cField2 = customFieldManager.getCustomFieldObjectByName("Incident End Time")
 Date incidentEnd = issue.getCustomFieldValue(cField2)


    //check if there the field has been filled out
if (incidentStart != null|| incidentEnd != null){
    // call the function to get the incident duration
    incidentDuration = getDateDiff(incidentStart,incidentEnd,TimeUnit.MINUTES) + " mins";
}
else {
    incidentDuration = " ";
}




// Set the confluence link up and make sure it works
def confluenceLink = getPrimaryConfluenceLink()
assert confluenceLink


// function that changes date format to be more readable
public static String convertDate(Date date){
    DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    String dateString = dateFormat.format(date);
    return dateString;
}


// get the field value
Date createdDate = issue.created;


// call the function to get the correct format
def createdDateFormatted = convertDate(createdDate);
def incidentStartFormatted = convertDate(incidentStart);
def incidentEndFormatted = convertDate();
