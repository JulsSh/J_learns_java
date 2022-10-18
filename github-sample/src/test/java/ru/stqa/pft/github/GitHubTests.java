package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

public class GitHubTests {
  @Test
  public void testCommits(){
    Github github = new RtGithub(" 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");
   RepoCommits commits= github.repos().get(new Coordinates.Simple("JulsSh", "j_learns_java")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(commit);

    }
  }
}
