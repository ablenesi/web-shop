using System;

using WebShop.BL.BusinessLayerContracts;
using WebShop.DL.DataLayerContract;

namespace WebShop.BL.BusinessLayerImpl
{
    public class UnitOfWork : IUnitOfWork
    {
        private IRepository _repository;
        private IProductOperations _productOperations;

        public UnitOfWork(IRepository repository)
        {
            _repository = repository;
        }

        public IProductOperations ProductOperations
        {
            get 
            {
                if (_productOperations == null)
                {
                    _productOperations = new ProductOperations(
                        _repository.ProductRepository,
                        _repository.CategoryRepository);
                }

                return _productOperations;
            }
        }

        public void SaveChanges()
        {
            _repository.SaveChanges();
        }
    }
}
