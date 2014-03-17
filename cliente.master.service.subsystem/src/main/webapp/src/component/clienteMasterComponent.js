define(['controller/selectionController', 'model/cacheModel', 'model/clienteMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/clienteComponent',
 'component/facturaComponent'
 ,
 'component/carritoDeComprasComponent'
 ,
 'component/productoComponent'
 
 ],function(SelectionController, CacheModel, ClienteMasterModel, CRUDComponent, TabController, ClienteComponent,
 FacturaComponent
 ,
 CarritoDeComprasComponent
 ,
 ProductoComponent
 ) {
    App.Component.ClienteMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('clienteMaster');
            var uComponent = new ClienteComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-cliente-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-cliente-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-cliente-list', function() {
                self.hideChilds();
            });
            Backbone.on('cliente-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'cliente-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-cliente-save', function(params) {
                self.model.set('clienteEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var facturaModels = self.facturaComponent.componentController.facturaModelList;
                self.model.set('listFactura', []);
                self.model.set('createFactura', []);
                self.model.set('updateFactura', []);
                self.model.set('deleteFactura', []);
                for (var i = 0; i < facturaModels.models.length; i++) {
                    var m = facturaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createFactura').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateFactura').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < facturaModels.deletedModels.length; i++) {
                    var m = facturaModels.deletedModels[i];
                    self.model.get('deleteFactura').push(m.toJSON());
                }
                var carritoDeComprasModels = self.carritoDeComprasComponent.componentController.carritoDeComprasModelList;
                self.model.set('listCarritoDeCompras', []);
                self.model.set('createCarritoDeCompras', []);
                self.model.set('updateCarritoDeCompras', []);
                self.model.set('deleteCarritoDeCompras', []);
                for (var i = 0; i < carritoDeComprasModels.models.length; i++) {
                    var m = carritoDeComprasModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createCarritoDeCompras').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateCarritoDeCompras').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < carritoDeComprasModels.deletedModels.length; i++) {
                    var m = carritoDeComprasModels.deletedModels[i];
                    self.model.get('deleteCarritoDeCompras').push(m.toJSON());
                }
                var productoModels = self.productoComponent.componentController.productoModelList;
                self.model.set('listProducto', []);
                self.model.set('createProducto', []);
                self.model.set('updateProducto', []);
                self.model.set('deleteProducto', []);
                for (var i = 0; i < productoModels.models.length; i++) {
                    var m = productoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createProducto').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateProducto').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < productoModels.deletedModels.length; i++) {
                    var m = productoModels.deletedModels[i];
                    self.model.get('deleteProducto').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Factura", name: "factura", enable: true},
                            ,
                            {label: "CarritoDeCompras", name: "carritoDeCompras", enable: true},
                            ,
                            {label: "Producto", name: "producto", enable: true},
                            ,
                            {label: "Ensayo", name: "ensayo", enable: true}
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.ClienteMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.facturaComponent = new FacturaComponent();
                    self.facturaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.FacturaModel), self.model.get('listFactura'));
                    self.facturaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.FacturaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.FacturaModel, App.Model.FacturaList, self.facturaModels)
                    });
                    self.facturaComponent.render(self.tabs.getTabHtmlId('factura'));
                    Backbone.on(self.facturaComponent.componentId + '-post-factura-create', function(params) {
                        params.view.currentFacturaModel.setCacheList(params.view.facturaModelList);
                    });
					self.carritoDeComprasComponent = new CarritoDeComprasComponent();
                    self.carritoDeComprasModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.CarritoDeComprasModel), self.model.get('listCarritoDeCompras'));
                    self.carritoDeComprasComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.CarritoDeComprasModel),
                        listModelClass: App.Utils.createCacheList(App.Model.CarritoDeComprasModel, App.Model.CarritoDeComprasList, self.carritoDeComprasModels)
                    });
                    self.carritoDeComprasComponent.render(self.tabs.getTabHtmlId('carritoDeCompras'));
                    Backbone.on(self.carritoDeComprasComponent.componentId + '-post-carritoDeCompras-create', function(params) {
                        params.view.currentCarritoDeComprasModel.setCacheList(params.view.carritoDeComprasModelList);
                    });
					self.productoComponent = new ProductoComponent();
                    self.productoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ProductoModel), self.model.get('listProducto'));
                    self.productoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ProductoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ProductoModel, App.Model.ProductoList, self.productoModels)
                    });
                    self.productoComponent.render(self.tabs.getTabHtmlId('producto'));
                    Backbone.on(self.productoComponent.componentId + '-post-producto-create', function(params) {
                        params.view.currentProductoModel.setCacheList(params.view.productoModelList);
                    });
                    self.facturaToolbarModel = self.facturaComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.facturaComponent.setToolbarModel(self.facturaToolbarModel);                    
                    self.carritoDeComprasToolbarModel = self.carritoDeComprasComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.carritoDeComprasComponent.setToolbarModel(self.carritoDeComprasToolbarModel);                    
                    self.productoToolbarModel = self.productoComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.productoComponent.setToolbarModel(self.productoToolbarModel);
                	
                     
                
                    Backbone.on(self.productoComponent.componentId + '-toolbar-button1', function() {
                        var selection = new App.Controller.SelectionController();
                        App.Utils.getComponentList('productoComponent', function(componentName, model) {
                            if (model.models.length == 0) {
                                alert('There is no productos to select.');
                            } else {
                                selection.showSelectionList({list: model, name: 'name', title: 'Producto List'});
                            }
                            ;
                        });
                    });
                    Backbone.on('post-selection', function(model2) {
                        
                        var model = new self.productoComponent.componentController.modelClass(model2.toJSON());
                        
                        model.setCacheList(self.productoComponent.componentController.productoModelList);
                        model.save({
                            success: function() {
                                self.productoComponent.componentController.list();
                            }
                        });
                    });
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.ClienteMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.ClienteMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.ClienteMasterComponent;
});