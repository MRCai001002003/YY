require.config({
    baseUrl: './resources/dist/',
    paths: {
        'angular': '../library/angular/angular.min',
        'ui.router': '../library/angular-ui-router/release/angular-ui-router.min',
        'angular.animate': '../library/angular-animate/angular-animate.min',
        'angular.sanitize': '../library/angular-sanitize/angular-sanitize.min',
		'angular.messages':'../library/angular-messages/angular-messages.min',
        'iScroll': '../library/iscroll/build/iscroll-probe.min',
        'hammer': '../library/hammerjs/hammer.min',
    },
//    urlArgs: 't=20160425',// + (new Date).getTime(),
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
        'angular.sanitize': ['angular'],
		'angular.messages':['angular']
    }
});
