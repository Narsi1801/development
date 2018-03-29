package com.innominds;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import jdk.nashorn.internal.ir.annotations.Ignore;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GithubPushEventApiApplicationTests {

	RestTemplate restClient = null;
	
	@Test
	public void contextLoads() {
		restClient = new RestTemplate();
	}

	@Before
	public void setUp() {
		restClient = new RestTemplate();
	}
	
	@After
	public void  tearDown() {
		restClient = null;
	}
	
	//@Test
	@Ignore
	public void testMonitor() {
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append(" \"ref\": \"refs/heads/master\",");
		str.append(" \"before\": \"18f77e6a7f4de06605c446897a6a3822d94eecef\",");
		str.append(" \"after\": \"521eff00efe364ad487fa9276805beb74504828c\",");
		str.append(" \"created\": false,");
		str.append(" \"deleted\": false,");
		str.append(" \"forced\": false,");
		str.append(" \"base_ref\": null,");
		str.append(" \"compare\": \"https://github.com/Dotridge/cloudbatch/compare/18f77e6a7f4d...521eff00efe3\",");
		str.append(" \"commits\": [");
		str.append(" {");
		str.append(" \"id\": \"521eff00efe364ad487fa9276805beb74504828c\",");
		str.append(" \"tree_id\": \"9c65e2b1a290693e76c7e875914f27d879221062\",");
		str.append(" \"distinct\": true,");
		str.append(" \"message\": \"added new json files\",");
		str.append(" \"timestamp\": \"2018-03-15T14:44:48+05:30\",");
		str.append(" \"url\": \"https://github.com/Dotridge/cloudbatch/commit/521eff00efe364ad487fa9276805beb74504828c\",");
		str.append(" \"author\": {");
		str.append(" \"name\": \"Narsi\",");
		str.append(" \"email\": \"sanna11@ca.com\"");
		str.append(" },");
		str.append(" \"committer\": {");
		str.append(" \"name\": \"Narsi\",");
		str.append(" \"email\": \"sanna11@ca.com\"");
		str.append(" },");
		str.append(" \"added\": [");
		str.append(" \"os-admin-actions.json\",");
		str.append(" \"os-agents.json\"");
		str.append(" ],");
		str.append(" \"removed\": [");
		str.append(" \"orchestration-api.json\"");
		str.append(" ],");
		str.append(" \"modified\": [");
		str.append("");
		str.append(" ]");
		str.append(" }");
		str.append(" ],");
		str.append(" \"head_commit\": {");
		str.append(" \"id\": \"521eff00efe364ad487fa9276805beb74504828c\",");
		str.append(" \"tree_id\": \"9c65e2b1a290693e76c7e875914f27d879221062\",");
		str.append(" \"distinct\": true,");
		str.append(" \"message\": \"added new json files\",");
		str.append(" \"timestamp\": \"2018-03-15T14:44:48+05:30\",");
		str.append(" \"url\": \"https://github.com/Dotridge/cloudbatch/commit/521eff00efe364ad487fa9276805beb74504828c\",");
		str.append(" \"author\": {");
		str.append(" \"name\": \"Narsi\",");
		str.append(" \"email\": \"sanna11@ca.com\"");
		str.append(" },");
		str.append(" \"committer\": {");
		str.append(" \"name\": \"Narsi\",");
		str.append(" \"email\": \"sanna11@ca.com\"");
		str.append(" },");
		str.append(" \"added\": [");
		str.append(" \"os-admin-actions.json\",");
		str.append(" \"os-agents.json\"");
		str.append(" ],");
		str.append(" \"removed\": [");
		str.append(" \"orchestration-api.json\"");
		str.append(" ],");
		str.append(" \"modified\": [");
		str.append("");
		str.append(" ]");
		str.append(" },");
		str.append(" \"repository\": {");
		str.append(" \"id\": 120929498,");
		str.append(" \"name\": \"cloudbatch\",");
		str.append(" \"full_name\": \"Dotridge/cloudbatch\",");
		str.append(" \"owner\": {");
		str.append(" \"name\": \"Dotridge\",");
		str.append(" \"email\": \"33683688+Dotridge@users.noreply.github.com\",");
		str.append(" \"login\": \"Dotridge\",");
		str.append(" \"id\": 33683688,");
		str.append(" \"avatar_url\": \"https://avatars3.githubusercontent.com/u/33683688?v=4\",");
		str.append(" \"gravatar_id\": \"\",");
		str.append(" \"url\": \"https://api.github.com/users/Dotridge\",");
		str.append(" \"html_url\": \"https://github.com/Dotridge\",");
		str.append(" \"followers_url\": \"https://api.github.com/users/Dotridge/followers\",");
		str.append(" \"following_url\": \"https://api.github.com/users/Dotridge/following{/other_user}\",");
		str.append(" \"gists_url\": \"https://api.github.com/users/Dotridge/gists{/gist_id}\",");
		str.append(" \"starred_url\": \"https://api.github.com/users/Dotridge/starred{/owner}{/repo}\",");
		str.append(" \"subscriptions_url\": \"https://api.github.com/users/Dotridge/subscriptions\",");
		str.append(" \"organizations_url\": \"https://api.github.com/users/Dotridge/orgs\",");
		str.append(" \"repos_url\": \"https://api.github.com/users/Dotridge/repos\",");
		str.append(" \"events_url\": \"https://api.github.com/users/Dotridge/events{/privacy}\",");
		str.append(" \"received_events_url\": \"https://api.github.com/users/Dotridge/received_events\",");
		str.append(" \"type\": \"User\",");
		str.append(" \"site_admin\": false");
		str.append(" },");
		str.append(" \"private\": false,");
		str.append(" \"html_url\": \"https://github.com/Dotridge/cloudbatch\",");
		str.append(" \"description\": null,");
		str.append(" \"fork\": false,");
		str.append(" \"url\": \"https://github.com/Dotridge/cloudbatch\",");
		str.append(" \"forks_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/forks\",");
		str.append(" \"keys_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/keys{/key_id}\",");
		str.append(" \"collaborators_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/collaborators{/collaborator}\",");
		str.append(" \"teams_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/teams\",");
		str.append(" \"hooks_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/hooks\",");
		str.append(" \"issue_events_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/issues/events{/number}\",");
		str.append(" \"events_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/events\",");
		str.append(" \"assignees_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/assignees{/user}\",");
		str.append(" \"branches_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/branches{/branch}\",");
		str.append(" \"tags_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/tags\",");
		str.append(" \"blobs_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/git/blobs{/sha}\",");
		str.append(" \"git_tags_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/git/tags{/sha}\",");
		str.append(" \"git_refs_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/git/refs{/sha}\",");
		str.append(" \"trees_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/git/trees{/sha}\",");
		str.append(" \"statuses_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/statuses/{sha}\",");
		str.append(" \"languages_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/languages\",");
		str.append(" \"stargazers_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/stargazers\",");
		str.append(" \"contributors_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/contributors\",");
		str.append(" \"subscribers_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/subscribers\",");
		str.append(" \"subscription_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/subscription\",");
		str.append(" \"commits_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/commits{/sha}\",");
		str.append(" \"git_commits_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/git/commits{/sha}\",");
		str.append(" \"comments_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/comments{/number}\",");
		str.append(" \"issue_comment_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/issues/comments{/number}\",");
		str.append(" \"contents_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/contents/{+path}\",");
		str.append(" \"compare_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/compare/{base}...{head}\",");
		str.append(" \"merges_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/merges\",");
		str.append(" \"archive_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/{archive_format}{/ref}\",");
		str.append(" \"downloads_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/downloads\",");
		str.append(" \"issues_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/issues{/number}\",");
		str.append(" \"pulls_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/pulls{/number}\",");
		str.append(" \"milestones_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/milestones{/number}\",");
		str.append(" \"notifications_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/notifications{?since,all,participating}\",");
		str.append(" \"labels_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/labels{/name}\",");
		str.append(" \"releases_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/releases{/id}\",");
		str.append(" \"deployments_url\": \"https://api.github.com/repos/Dotridge/cloudbatch/deployments\",");
		str.append(" \"created_at\": 1518194047,");
		str.append(" \"updated_at\": \"2018-03-15T01:51:37Z\",");
		str.append(" \"pushed_at\": 1521105320,");
		str.append(" \"git_url\": \"git://github.com/Dotridge/cloudbatch.git\",");
		str.append(" \"ssh_url\": \"git@github.com:Dotridge/cloudbatch.git\",");
		str.append(" \"clone_url\": \"https://github.com/Dotridge/cloudbatch.git\",");
		str.append(" \"svn_url\": \"https://github.com/Dotridge/cloudbatch\",");
		str.append(" \"homepage\": null,");
		str.append(" \"size\": 47164,");
		str.append(" \"stargazers_count\": 0,");
		str.append(" \"watchers_count\": 0,");
		str.append(" \"language\": null,");
		str.append(" \"has_issues\": true,");
		str.append(" \"has_projects\": true,");
		str.append(" \"has_downloads\": true,");
		str.append(" \"has_wiki\": true,");
		str.append(" \"has_pages\": false,");
		str.append(" \"forks_count\": 0,");
		str.append(" \"mirror_url\": null,");
		str.append(" \"archived\": false,");
		str.append(" \"open_issues_count\": 0,");
		str.append(" \"license\": null,");
		str.append(" \"forks\": 0,");
		str.append(" \"open_issues\": 0,");
		str.append(" \"watchers\": 0,");
		str.append(" \"default_branch\": \"master\",");
		str.append(" \"stargazers\": 0,");
		str.append(" \"master_branch\": \"master\"");
		str.append(" },");
		str.append(" \"pusher\": {");
		str.append(" \"name\": \"Dotridge\",");
		str.append(" \"email\": \"33683688+Dotridge@users.noreply.github.com\"");
		str.append(" },");
		str.append(" \"sender\": {");
		str.append(" \"login\": \"Dotridge\",");
		str.append(" \"id\": 33683688,");
		str.append(" \"avatar_url\": \"https://avatars3.githubusercontent.com/u/33683688?v=4\",");
		str.append(" \"gravatar_id\": \"\",");
		str.append(" \"url\": \"https://api.github.com/users/Dotridge\",");
		str.append(" \"html_url\": \"https://github.com/Dotridge\",");
		str.append(" \"followers_url\": \"https://api.github.com/users/Dotridge/followers\",");
		str.append(" \"following_url\": \"https://api.github.com/users/Dotridge/following{/other_user}\",");
		str.append(" \"gists_url\": \"https://api.github.com/users/Dotridge/gists{/gist_id}\",");
		str.append(" \"starred_url\": \"https://api.github.com/users/Dotridge/starred{/owner}{/repo}\",");
		str.append(" \"subscriptions_url\": \"https://api.github.com/users/Dotridge/subscriptions\",");
		str.append(" \"organizations_url\": \"https://api.github.com/users/Dotridge/orgs\",");
		str.append(" \"repos_url\": \"https://api.github.com/users/Dotridge/repos\",");
		str.append(" \"events_url\": \"https://api.github.com/users/Dotridge/events{/privacy}\",");
		str.append(" \"received_events_url\": \"https://api.github.com/users/Dotridge/received_events\",");
		str.append(" \"type\": \"User\",");
		str.append(" \"site_admin\": false");
		str.append(" }");
		str.append("}");
		
		String payLoad = str.toString();
		String uri = "http://localhost:8080/monitor";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		
		HttpEntity entity = new HttpEntity(payLoad, headers);
		
		ResponseEntity<String> resp = restClient.postForEntity(uri, entity, String.class);
		
		System.out.println("response is:\t"+resp.getBody());
	}
}
