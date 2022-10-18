package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
   RepoCommits commits= github.repos().get(new Coordinates.Simple("JulsSh", "j_learns_java")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());

    }
  }
}
