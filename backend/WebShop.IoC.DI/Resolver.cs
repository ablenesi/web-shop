using StructureMap;
using System;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerImpl;
using WebShop.DL.DataLayerContract;
using WebShop.DL.EFDataLayer;

namespace WebShop.IoC.DI
{
    public static class Resolver
    {
        private static Container _container;

        static Resolver()
        {
            _container = new Container((x) =>
            {
                x.For<IUnitOfWork>().Use<UnitOfWork>();
                x.For<IRepository>().Use<EFRepository>();
            });
        }

        public static IContainer Container 
        {
            get
            {
                return _container;
            }
        }

        public static T Get<T>()
            where T : class
        {
            return _container.GetInstance<T>();
        }

        public static object Get(Type type)
        {
            return _container.GetInstance(type);
        }
    }
}
