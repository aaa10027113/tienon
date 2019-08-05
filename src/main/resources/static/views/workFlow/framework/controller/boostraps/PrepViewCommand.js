puremvc.define({
    name: 'ejx4ui.controller.command.PrepViewCommand',
    parent: puremvc.SimpleCommand
	}, {
    execute: function (note) {
        this.facade.registerMediator(new ejx4ui.view.mediator.CommonMediator());
    }
   }
);
