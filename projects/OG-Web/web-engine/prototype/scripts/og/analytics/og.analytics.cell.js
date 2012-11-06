/*
 * Copyright 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.analytics.Cell',
    dependencies: ['og.api.rest', 'og.analytics.Data', 'og.common.events'],
    obj: function () {
        var module = this, events = og.common.events, constructor = function (config, label) {
            var cell = this;
            label = label ? '[' + label + ']' : '';
            var fatal_handler = function (message) {
                try {cell.fire('fatal', message);}
                catch (error) {og.dev.warn(module.name + ': a fatal handler threw ', error);}
            };
            events.register.call(cell, 'data', 'fatal');
            cell.dataman = new og.analytics.Data(config.source, true, 'cell' + label)
                .on('meta', function (meta) {
                    cell.dataman.viewport({rows: [config.row], cols: [config.col], format: config.format});
                })
                .on('data', function (data) {
                    var cell_data = data[0];
                    try {cell.fire('data', cell_data);} catch (error) {
                        cell.kill();
                        fatal_handler(module.name + ': a data handler threw error: ' +
                            (error.message || 'an unknown error') + ' (check console.$)');
                        throw error; // let og.analytics.Data catch it
                    }
                })
                .on('fatal', fatal_handler);
        };
        constructor.prototype.kill = function () {this.dataman.kill();};
        constructor.prototype.off = events.off;
        constructor.prototype.on = events.on;
        return constructor;
    }
});