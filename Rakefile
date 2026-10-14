# frozen_string_literal: true

begin
  require 'github_changelog_generator/task'
rescue LoadError
  task :changelog do
    abort('Run `bundle install --with release` to install the `github_changelog_generator` gem.')
  end
else
  GitHubChangelogGenerator::RakeTask.new :changelog do |config|
    config.header = <<~HEADER.chomp
      # Changelog

      All notable changes to this project will be documented in this file.
    HEADER
    config.user = 'OpenVoxProject'
    config.project = 'jruby-deps'
    config.exclude_labels = %w[dependencies duplicate question invalid wontfix wont-fix modulesync skip-changelog]
    config.future_release = File.readlines('project.clj')
      .grep(/^\(defproject /).first[/"([^"]+)"/, 1]
      .sub(/-SNAPSHOT\z/, '')
    # The generated changelog starts with 9.4.8.0-2, the first release after the move to OpenVoxProject.
    # Only tags on main count, so releases from the 9.x and 10.0.x branches stay out.
    config.since_tag = '9.3.15.0-1'
    config.include_tags_regex = /\A9\.3\.15\.0-1\z|\A9\.4\.8\.0-[23]\z|\A9\.4\.12\.1-[123]\z|\A9\.4\.14\.0-[12]\z|\A9\.4\.15\.0-1\z|\A10\.0\.6\.0-1\z|\A10\.1\./
    config.release_branch = 'main'
  end
end

namespace :vox do
  desc 'Set the version in project.clj for the release'
  task 'version:bump:full', [:version] do |_, args|
    version = args[:version]
    abort 'You must provide a version.' if version.nil? || version.empty?

    project = File.read('project.clj')
    defproject = /^(\(defproject\s+\S+\s+)"[^"]+"/
    abort 'Could not find the defproject version in project.clj' unless project.match?(defproject)

    File.write('project.clj', project.sub(defproject) { "#{Regexp.last_match(1)}\"#{version}\"" })
    puts "Set the version in project.clj to #{version}"
  end
end

desc 'Prepare for a release'
task 'release:prepare' => [:changelog]
