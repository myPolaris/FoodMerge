

// SystemJS support.
window.self = window;
require("src/system.bundle.f45da.js");

const importMapJson = jsb.fileUtils.getStringFromFile("src/import-map.4bc91.json");
const importMap = JSON.parse(importMapJson);
System.warmup({
    importMap,
    importMapUrl: 'src/import-map.4bc91.json',
    defaultHandler: (urlNoSchema) => {
        require(urlNoSchema.startsWith('/') ? urlNoSchema.substr(1) : urlNoSchema);
    },
});

System.import('./application.fb5a7.js')
.then(({ Application }) => {
    return new Application();
}).then((application) => {
    return System.import('cc').then((cc) => {
        require('jsb-adapter/engine-adapter.js');
        return application.init(cc);
    }).then(() => {
        return application.start();
    });
}).catch((err) => {
    console.error(err.toString() + ', stack: ' + err.stack);
});
