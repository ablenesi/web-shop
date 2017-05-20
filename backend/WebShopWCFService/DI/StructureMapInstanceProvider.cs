using System;
using System.ServiceModel;
using System.ServiceModel.Channels;
using System.ServiceModel.Dispatcher;
using WebShop.IoC.DI;

namespace WebShopWCFService.DI
{
    public class StructureMapInstanceProvider : IInstanceProvider
    {
        private readonly Type _serviceType;

        public StructureMapInstanceProvider(Type serviceType)
        {
            _serviceType = serviceType;
        }

        public object GetInstance(InstanceContext instanceContext)
        {
            return GetInstance(instanceContext, null);
        }

        public object GetInstance(InstanceContext instanceContext, Message message)
        {
            return Resolver.Get(_serviceType);
        }

        public void ReleaseInstance(InstanceContext instanceContext, object instance)
        {
        }
    }
}