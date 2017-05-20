using System;

using WebShop.BL.BusinessLayerContracts;
using WebShop.DL.DataLayerContract;

namespace WebShop.BL.BusinessLayerImpl
{
    public class UnitOfWork : IUnitOfWork
    {
        private IRepository _repository;
        private IProductOperations _productOperations;
        private ICategoryOperations _categoryOperations;
        private IOrderOperations _orderOperations;

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

        public ICategoryOperations CategoryOperations
        {
            get
            {
                if (_categoryOperations == null)
                {
                    _categoryOperations = new CategoryOperations(
                        _repository.CategoryRepository);
                }

                return _categoryOperations;
            }
        }

        public IOrderOperations OrderOperations
        {
            get
            {
                if (_orderOperations == null)
                {
                    _orderOperations = new OrderOperations(
                        _repository.OrderRepository,
                        _repository.OrderDetailRepository);
                }

                return _orderOperations;
            }
        }

        public void SaveChanges()
        {
            _repository.SaveChanges();
        }
    }
}
