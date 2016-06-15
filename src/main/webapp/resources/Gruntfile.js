module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        htmlmin: {
            options: {
                //删除HTML注释
                removeComments: true,
                //删除<script>和<style>标签内的HTML注释
                removeCommentsFromCDATA: true,
                //删除文档树中文本节点的空白。不会影响重大的空白，比如在SCRIPT,STYLE,PRE或TEXTAREA等元素内容。
                collapseWhitespace: true,
                //删除布尔属性
                collapseBooleanAttributes: true,
                //删除属性的引号，当安全的情况下。
                removeAttributeQuotes: true,
                //删除多余的属性，像type="text/javascript"@@@请勿开启。
                removeRedundantAttributes: false,
                //用短的HTML5的<!DOCTYPE html>代替doctype
                useShortDoctype: true,
                //删除空（或空白）属性
                removeEmptyAttributes: true,
                //一些元素允许省略标签，像</td>
                removeOptionalTags: true
            },
            tpl: {
                files: [{
                    expand: true,
                    cwd: 'src/',
                    src: ['**/*.html'],
                    dest: 'dist/',
                    ext: '.html'
                }]
            }
        },
        cssmin: {
            build: {
                files: [{
                    expand: true,
                    cwd: './src/common/',
                    src: '**/*.css',
                    dest: './dist/common/'
                }]
            }
        },
        copy: {
            build: {
                files: [{
                    expand: true,
                    cwd: './src/library/',
                    src: '**/*',
                    dest: './dist/library/'
                }]
            }
        },
        requirejs: {
            compile: {
                options: {
                    paths: {
                        'angular': 'empty:',
                        'ui.router': 'empty:',
                        'angular.animate': 'empty:',
                        'angular.sanitize': 'empty:',
                        'angular.messages': 'empty:',
                        'iScroll': 'empty:',
                        'hammer': 'empty:'
                    },
                    shim: {
                        'angular': {
                            exports: 'angular'
                        },
                        'iScroll': {
                            exports: 'IScroll'
                        },
                        'hammer': {
                            exports: 'Hammer'
                        },
                        'ui.router': ['angular'],
                        'angular.animate': ['angular'],
                        'angular.messages': ['angular'],
                        'angular.sanitize': ['angular'],
                        'angular.validate': ['angular']
                    },
                    baseUrl: './',
                    dir: 'dist',
                    appDir: 'src',
                    keepBuildDir: true,
                    modules: [{
                        name: 'app'
                    }],
                    fileExclusionRegExp: /^library/
                }
            }
        },
		clean:{
			css:['./dist/**/*.scss','./dist/**/*.map']
		}
    });
    grunt.loadNpmTasks('grunt-contrib-requirejs');
    grunt.loadNpmTasks('grunt-contrib-htmlmin');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-clean');



    grunt.registerTask('default', ['requirejs:compile', 'htmlmin:tpl', 'cssmin:build', 'clean:css'])
    grunt.registerTask('build', ['requirejs:build', 'htmlmin'])
}
