using System;
using System.ServiceModel;
using System.ServiceModel.Activation;

namespace WebShopWCFService.DI
{
    public class StructureMapServiceHostFactory : ServiceHostFactory
    {
        public StructureMapServiceHostFactory()
        {
        }

        protected override ServiceHost CreateServiceHost(Type serviceType, Uri[] baseAddresses)
        {
            return new StructureMapServiceHost(serviceType, baseAddresses);
        }
    }
}